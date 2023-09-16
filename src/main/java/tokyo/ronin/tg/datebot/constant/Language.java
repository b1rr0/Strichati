package tokyo.ronin.tg.datebot.constant;

import java.util.Arrays;
import java.util.Optional;

public enum Language {
    UKRAINE("ua"), ENGLISH("en");
    private final String data;

    Language(String value) {
        this.data = value;
    }

    public String getData() {
        return data;
    }

    public static Optional<Language> getByData(String data) {
        return Arrays.stream(values())
                .filter(languageCode -> languageCode.data.equals(data))
                .findFirst();
    }
}
