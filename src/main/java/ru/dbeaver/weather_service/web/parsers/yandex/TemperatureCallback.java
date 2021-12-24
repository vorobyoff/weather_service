package ru.dbeaver.weather_service.web.parsers.yandex;

import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import java.util.Optional;
import java.util.regex.Pattern;

import static java.util.Optional.ofNullable;

public final class TemperatureCallback extends ParserCallback {

    private static final Pattern WHETHER_PATTERN = Pattern.compile("−?\\d+°");
    private String temperature;

    @Override
    public void handleText(char[] data, int position) {
        final String txt = new String(data).trim();
        if (WHETHER_PATTERN.matcher(txt).matches()) {
            temperature = txt;
        }
    }

    public Optional<String> getTemperature() {
        return ofNullable(temperature);
    }
}