package ru.dbeaver.weather_service.services.mappers;

import org.springframework.stereotype.Component;
import ru.dbeaver.weather_service.repositories.model.Weather;
import ru.dbeaver.weather_service.web.dto.WeatherDto;

@Component
public final class WeatherMapper implements Mapper<Weather, WeatherDto> {

    @Override
    public WeatherDto map(final Weather weather) {
        if (weather == null) return null;
        return new WeatherDto(weather.getTemperature());
    }
}