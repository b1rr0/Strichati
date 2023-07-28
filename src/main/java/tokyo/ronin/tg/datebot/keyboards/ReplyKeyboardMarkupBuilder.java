package tokyo.ronin.tg.datebot.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboardMarkupBuilder {
    private final List<KeyboardRow> keyboard = new ArrayList<>();
    private KeyboardRow row = null;

    private ReplyKeyboardMarkupBuilder() {
    }

    public static ReplyKeyboardMarkupBuilder create() {
        return new ReplyKeyboardMarkupBuilder();
    }

    public ReplyKeyboardMarkupBuilder addTextButton(String text) {
        row.add(text);
        return this;
    }

    public ReplyKeyboardMarkupBuilder addButton(KeyboardButton button) {
        row.add(button);
        return this;
    }

    public ReplyKeyboardMarkupBuilder nextRow() {
        if (row != null && row.size() > 0) {
            keyboard.add(row);
        }
        row = new KeyboardRow();
        return this;
    }

    public ReplyKeyboardMarkup build() {
        if (row.size() != 0) {
            keyboard.add(row);
        }

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
