package ru.dbeaver.weather_service.services.mappers;

import org.springframework.stereotype.Component;
import ru.dbeaver.weather_service.repositories.model.Weather;
import ru.dbeaver.weather_service.web.dto.WeatherDto;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Component
public final class WeatherDtoMapper implements Mapper<WeatherDto, Weather> {

    @Override
    public Weather map(final WeatherDto weatherDto) {
        if (isNull(weatherDto)) return null;
        if (weatherDto.isEmpty()) return null;

        return new Weather(weatherDto.getTemp(), LocalDate.now()); // date sets only to reduce nulls
    }
}