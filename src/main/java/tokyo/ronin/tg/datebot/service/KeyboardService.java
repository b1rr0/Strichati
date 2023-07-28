package tokyo.ronin.tg.datebot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.Map;

import tokyo.ronin.tg.datebot.contoller.Status;
import tokyo.ronin.tg.datebot.keyboards.Description;

@Service
public class KeyboardService {

    private Map<Status, ReplyKeyboardMarkup> map = new HashMap<>();

    public KeyboardService() {
        init();
    }

    public ReplyKeyboardMarkup getKeyboard(Status status){
        return  map.get(status);
    }
    private void init() {
      map.put(Status.SETTING_LOCATION, Description.locationKeyboard());
    }
}
