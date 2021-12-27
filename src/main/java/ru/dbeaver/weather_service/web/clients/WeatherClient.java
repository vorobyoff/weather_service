package ru.dbeaver.weather_service.web.clients;

import ru.dbeaver.weather_service.web.dto.WeatherDto;

import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public abstract class WeatherClient {

    private final HttpClient client;

    protected WeatherClient(final HttpClient client) {
        this.client = client;
    }

    public final CompletableFuture<WeatherDto> request() {
        return client.sendAsync(getRequest(), BodyHandlers.ofInputStream())
                .thenApplyAsync(HttpResponse::body)
                .thenApplyAsync(this::parse);
    }

    protected abstract HttpRequest getRequest();

    protected abstract WeatherDto parse(final InputStream in);
}