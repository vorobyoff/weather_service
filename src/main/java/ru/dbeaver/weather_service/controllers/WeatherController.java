package ru.dbeaver.weather_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dbeaver.weather_service.services.WeatherService;
import ru.dbeaver.weather_service.web.dto.WeatherDto;

@RestController
public final class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public WeatherDto getCurrentWeather() {
        return weatherService.getCurrentWeather();
    }
}