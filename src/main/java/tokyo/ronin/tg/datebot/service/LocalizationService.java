package tokyo.ronin.tg.datebot.service;

import tokyo.ronin.tg.datebot.constant.Language;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LocalizationService {
    static Map<Language, ResourceBundle> map = new HashMap<>();
    static {
        map.put(Language.UKRAINE, ResourceBundle.getBundle("i18n.ua"));
        map.put(Language.ENGLISH, ResourceBundle.getBundle("i18n.en"));
    }

    public static String getData(Language language, String key) {
        return map.get(language).getString(key);
    }
}