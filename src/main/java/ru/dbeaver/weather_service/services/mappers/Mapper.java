package ru.dbeaver.weather_service.services.mappers;

public interface Mapper<T, R> {

    R map(T t);
}