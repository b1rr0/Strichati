package tokyo.ronin.tg.datebot.constant;

public enum Language {
    UKRAINE("ua"), ENGLISH("en");
    private final String data;

    Language(String value) {
        this.data = value;
    }

    public String getData() {
        return data;
    }

    public static Language getByData(String data) {
        for (Language languageCode : values()) {
            if (languageCode.data.equals(data)) {
                return languageCode;
            }
        }
        return null;
    }
}
