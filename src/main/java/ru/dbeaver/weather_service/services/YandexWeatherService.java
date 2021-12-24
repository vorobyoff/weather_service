package ru.dbeaver.weather_service.services;

import org.springframework.stereotype.Service;
import ru.dbeaver.weather_service.repositories.WeatherRepository;
import ru.dbeaver.weather_service.repositories.model.Weather;
import ru.dbeaver.weather_service.services.mappers.Mapper;
import ru.dbeaver.weather_service.services.tx.Transactor;
import ru.dbeaver.weather_service.web.clients.WeatherClient;
import ru.dbeaver.weather_service.web.dto.WeatherDto;

import java.time.LocalDate;

@Service
public final class YandexWeatherService implements WeatherService {

    private final Mapper<Weather, WeatherDto> weatherMapper;
    private final Mapper<WeatherDto, Weather> dtoMapper;
    private final WeatherRepository repository;
    private final WeatherClient client;
    private final Transactor tx;

    public YandexWeatherService(final Mapper<Weather, WeatherDto> weatherMapper, final Mapper<WeatherDto, Weather> dtoMapper,
                                final WeatherRepository repository, final WeatherClient client, final Transactor tx) {
        this.weatherMapper = weatherMapper;
        this.dtoMapper = dtoMapper;
        this.repository = repository;
        this.client = client;
        this.tx = tx;
    }

    @Override
    public WeatherDto getCurrentWeather() {
        final var now = LocalDate.now();
        final var currentWeather = repository.findWeatherByDate(now);
        if (currentWeather.isPresent()) return weatherMapper.map(currentWeather.get());
        return requestWeatherWithDate(now);
    }

    private WeatherDto requestWeatherWithDate(final LocalDate date) {
        return client.request()
                .thenApply(this::validateWeather)
                .thenApplyAsync(dtoMapper::map)
                .thenApply(w -> saveWeatherWithDate(date, w))
                .thenApplyAsync(weatherMapper::map)
                .join();
    }

    private WeatherDto validateWeather(final WeatherDto dto) {
        if (dto.isEmpty())
            throw new IllegalStateException("Cannot find current weather");
        return dto;
    }

    private Weather saveWeatherWithDate(final LocalDate date, final Weather weather) {
        // checking if such weather already exists due to race condition
        final var isWeatherAlreadyExists = tx.exec(() -> repository.findWeatherByDate(date));
        if (isWeatherAlreadyExists.isPresent()) {
            // if some thread has already saved the weather, we need to update it
            final var existedWeather = isWeatherAlreadyExists.get();
            existedWeather.setTemperature(weather.getTemperature());
            return tx.exec(() -> repository.save(existedWeather));
        }
        // if not one thread hasn't yet saved the weather
        weather.setDate(date);
        return tx.exec(() -> repository.save(weather));
    }
}