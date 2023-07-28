package tokyo.ronin.tg.datebot.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import tokyo.ronin.tg.datebot.constant.Button;

public class Description {

    public static ReplyKeyboardMarkup locationKeyboard() {

        KeyboardButton button = new KeyboardButton();
        button.setRequestLocation(true);

        button.setText(Button.SHARE_LOCATION.getValue());

        return ReplyKeyboardMarkupBuilder.create()
                .nextRow()
                .addButton(button)
                .build();
    }
}
