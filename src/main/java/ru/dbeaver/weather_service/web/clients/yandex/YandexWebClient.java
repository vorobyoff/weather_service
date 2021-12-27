package ru.dbeaver.weather_service.web.clients.yandex;

import org.springframework.stereotype.Component;
import ru.dbeaver.weather_service.web.clients.WeatherClient;
import ru.dbeaver.weather_service.web.dto.WeatherDto;
import ru.dbeaver.weather_service.web.parsers.WebPageParser;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Component
public final class YandexWebClient extends WeatherClient {

    private final WebPageParser<WeatherDto> parser;

    public YandexWebClient(final HttpClient client, final WebPageParser<WeatherDto> parser) {
        super(client);
        this.parser = parser;
    }

    @Override
    protected HttpRequest getRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create("https://yandex.ru/"))
                .build();
    }

    @Override
    protected WeatherDto parse(final InputStream in) {
        return parser.parse(in);
    }
}