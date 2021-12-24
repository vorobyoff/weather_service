package ru.dbeaver.weather_service.web.dto;

public final class WeatherDto {

    private static final WeatherDto NULL = new WeatherDto(null);
    private final String temp;

    public WeatherDto(final String temp) {
        this.temp = temp;
    }

    public static WeatherDto empty() {
        return new WeatherDto(null);
    }

    public boolean isEmpty() {
        return this == NULL;
    }

    public String getTemp() {
        return temp;
    }
}