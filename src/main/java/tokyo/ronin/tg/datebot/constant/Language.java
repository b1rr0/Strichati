package tokyo.ronin.tg.datebot.constant;

import java.util.Arrays;
import java.util.Optional;

public enum Language {
    UKRAINE("ua", ButtonsConstant.FLAG_UA), ENGLISH("en", ButtonsConstant.FLAG_EN);
    private final String data;
    private final String flag;

    Language(String value, String data2) {
        this.data = value;
        this.flag = data2;
    }

    public String getData() {
        return data;
    }

    public static Optional<Language> getByFlag(String flag) {
        return Arrays.stream(values())
                .filter(languageCode -> languageCode.flag.equals(flag))
                .findFirst();
    }
}
