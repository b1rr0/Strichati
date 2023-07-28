package tokyo.ronin.tg.datebot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

import tokyo.ronin.tg.datebot.bot.Bot;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;

@Service
public class SenderService {
    private final KeyboardService keyboardService;

    @Autowired
    public SenderService(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    public void addMessageToQueue(PersonWithMessageQueue personWithMessageQueue, String text) {
        Person person = personWithMessageQueue.getPerson();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(person.getId());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(keyboardService.getKeyboard(person.getStatus()));
        personWithMessageQueue.addMessage(sendMessage);
    }

}
