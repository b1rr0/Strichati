package tokyo.ronin.tg.datebot.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import tokyo.ronin.tg.datebot.constant.Language;
import tokyo.ronin.tg.datebot.service.LocalizationService;


public class ReplyKeyboardMarkupHandler {
    public interface KeyBoard {
        ReplyKeyboardMarkup create(Language language);
    }

    public static class LocationKeyboard implements KeyBoard {
        public ReplyKeyboardMarkup create(Language language) {
            KeyboardButton button = new KeyboardButton();
            button.setRequestLocation(true);

            button.setText(LocalizationService.getData(language, "locationKeyName"));

            return ReplyKeyboardMarkupBuilder.create()
                    .nextRow()
                    .addButton(button)
                    .build();
        }
    }

    public static class DefaultKeyboard implements KeyBoard {
        public ReplyKeyboardMarkup create(Language language) {
            KeyboardButton button1 = new KeyboardButton();
            button1.setText("1");

            KeyboardButton button2 = new KeyboardButton();
            button2.setText("2");

            KeyboardButton button3 = new KeyboardButton();
            button3.setText("3");

            KeyboardButton button4 = new KeyboardButton();
            button4.setText("4");

            return ReplyKeyboardMarkupBuilder.create()
                    .nextRow()
                    .addButton(button1)
                    .addButton(button2)
                    .addButton(button3)
                    .addButton(button4)
                    .build();
        }
    }

    // дивитися анкети //  моя анкета
    // вимкнути анкету // зманіти мову
    //  запроси друзів
}