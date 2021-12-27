package ru.dbeaver.weather_service.web.parsers.yandex;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import ru.dbeaver.weather_service.web.dto.WeatherDto;
import ru.dbeaver.weather_service.web.parsers.WebPageParser;

import javax.swing.text.html.HTMLEditorKit.Parser;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public final class YandexWebPageParserAdapter implements WebPageParser<WeatherDto> {

    private static final Logger LOG = getLogger(YandexWebPageParserAdapter.class);

    private final Parser delegator = new ParserDelegator();

    @Override
    public WeatherDto parse(final InputStream in) {
        final var callback = new TemperatureCallback();

        try {
            delegator.parse(new InputStreamReader(in, UTF_8), callback, true);
        } catch (IOException e) {
            LOG.error("Error occurred during parsing data from yandex.", e);
            return WeatherDto.empty();
        }

        final Optional<String> temperature = callback.getTemperature();
        return temperature.map(WeatherDto::new).orElseGet(WeatherDto::empty);
    }
}