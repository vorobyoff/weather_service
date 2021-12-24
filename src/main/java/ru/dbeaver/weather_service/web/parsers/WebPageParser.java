package ru.dbeaver.weather_service.web.parsers;

import java.io.InputStream;

public interface WebPageParser<R> {

    R parse(final InputStream in);
}