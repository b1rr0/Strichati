package tokyo.ronin.tg.datebot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import tokyo.ronin.tg.datebot.constant.Language;
import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.keyboards.ReplyKeyboardMarkupHandler;

import java.util.HashMap;
import java.util.Map;

@Service
public class KeyboardService {

    private final Map<PersonStatus, ReplyKeyboardMarkupHandler.KeyBoard> map = new HashMap<>();

    public KeyboardService() {
        init();
    }

    public ReplyKeyboardMarkup getKeyboard(Language language, PersonStatus personStatus) {
        return map.getOrDefault(personStatus, new ReplyKeyboardMarkupHandler.LocationKeyboard()).create(language);
    }
    // probable option with caching objects

    private void init() {
        map.put(PersonStatus.SETTING_LOCATION, new ReplyKeyboardMarkupHandler.LocationKeyboard());
        map.put(PersonStatus.DEFAULT, new ReplyKeyboardMarkupHandler.DefaultKeyboard());
    }
}
