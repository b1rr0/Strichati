package tokyo.ronin.tg.datebot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.Map;

import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.keyboards.Description;

@Service
public class KeyboardService {

    private final Map<PersonStatus, ReplyKeyboardMarkup> map = new HashMap<>();

    public KeyboardService() {
        init();
    }

    public ReplyKeyboardMarkup getKeyboard(PersonStatus personStatus){
        return  map.get(personStatus);
    }
    private void init() {
      map.put(PersonStatus.SETTING_LOCATION, Description.locationKeyboard());
    }
}
