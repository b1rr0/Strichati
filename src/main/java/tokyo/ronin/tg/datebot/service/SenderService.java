package tokyo.ronin.tg.datebot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import tokyo.ronin.tg.datebot.entity.UserEntity;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;

@Service
public class SenderService {
    private final KeyboardService keyboardService;

    @Autowired
    public SenderService(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    public void addMessageToQueue(PersonWithMessageQueue personWithMessageQueue, String text) {
        UserEntity userEntity = personWithMessageQueue.getPerson();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userEntity.getId());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(keyboardService.getKeyboard(userEntity.getLanguage(), userEntity.getStatus()));
        personWithMessageQueue.addMessage(sendMessage);
    }
}
