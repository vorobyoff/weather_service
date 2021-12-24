package ru.dbeaver.weather_service.repositories.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("WEATHER_HISTORY")
public final class Weather {

    @Id
    @Column("WEATHER_ID")
    private Long id;
    @Column("WEATHER_VALUE")
    private String temperature;
    @Column("WEATHER_DATE")
    private LocalDate date;

    public Weather(final String temperature, final LocalDate date) {
        this.temperature = temperature;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(final String temperature) {
        this.temperature = temperature;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }
}