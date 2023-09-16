package tokyo.ronin.tg.datebot.models;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import tokyo.ronin.tg.datebot.entity.UserEntity;

import java.util.LinkedList;
import java.util.Queue;

public class PersonWithMessageQueue {
    private final Queue<BotApiMethod<?>> messages = new LinkedList<>();
    private UserEntity userEntity;

    public PersonWithMessageQueue(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Queue<BotApiMethod<?>> getMessages() {
        return messages;
    }

    public void addMessage(BotApiMethodMessage message) {
        messages.add(message);
    }

    public UserEntity getPerson() {
        return userEntity;
    }

    public PersonWithMessageQueue setPerson(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @Override
    public String toString() {
        return "PersonWithMessageQueue{" +
                "messages=" + messages +
                ", person=" + userEntity +
                '}';
    }
}
