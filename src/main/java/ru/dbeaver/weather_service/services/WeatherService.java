package ru.dbeaver.weather_service.services;

import ru.dbeaver.weather_service.web.dto.WeatherDto;

public interface WeatherService {

    WeatherDto getCurrentWeather();
}