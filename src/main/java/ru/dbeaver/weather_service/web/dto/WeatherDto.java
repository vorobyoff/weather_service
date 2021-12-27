package ru.dbeaver.weather_service.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class WeatherDto {

    private static final WeatherDto NULL = new WeatherDto(null);
    private final String temp;

    public WeatherDto(final String temp) {
        this.temp = temp;
    }

    public static WeatherDto empty() {
        return NULL;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return this == NULL;
    }

    public String getTemp() {
        return temp;
    }
}