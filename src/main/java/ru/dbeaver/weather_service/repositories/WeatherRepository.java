package ru.dbeaver.weather_service.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dbeaver.weather_service.repositories.model.Weather;

import java.time.LocalDate;
import java.util.Optional;

public interface WeatherRepository extends CrudRepository<Weather, Long> {

    Optional<Weather> findWeatherByDate(final LocalDate date);
}