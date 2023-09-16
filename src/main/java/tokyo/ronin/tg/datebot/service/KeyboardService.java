package tokyo.ronin.tg.datebot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import tokyo.ronin.tg.datebot.constant.Language;
import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.keyboards.ReplyKeyboardMarkupHandler;

import java.util.HashMap;
import java.util.Map;

@Service
public class KeyboardService {

    private final Map<UserStatus, ReplyKeyboardMarkupHandler.KeyBoard> map = new HashMap<>();

    public KeyboardService() {
        init();
    }

    public ReplyKeyboardMarkup getKeyboard(Language language, UserStatus userStatus) {
        return map.getOrDefault(userStatus, new ReplyKeyboardMarkupHandler.LocationKeyboard()).create(language);
    }
    // probable option with caching objects

    private void init() {
        map.put(UserStatus.SETTING_LOCATION, new ReplyKeyboardMarkupHandler.LocationKeyboard());
        map.put(UserStatus.DEFAULT, new ReplyKeyboardMarkupHandler.DefaultKeyboard());
    }
}
