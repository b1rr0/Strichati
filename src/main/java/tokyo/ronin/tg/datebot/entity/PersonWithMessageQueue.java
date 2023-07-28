package tokyo.ronin.tg.datebot.entity;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;

import java.util.ArrayList;
import java.util.List;

public class PersonWithMessageQueue {
    private List<BotApiMethodMessage> messages = new ArrayList<>();
    private Person person;

    public PersonWithMessageQueue(Person person) {
        this.person = person;
    }

    public List<BotApiMethodMessage> getMessages() {
        return messages;
    }

    public void addMessage(BotApiMethodMessage message) {
      messages.add(message);
    }

    public Person getPerson() {
        return person;
    }

    public PersonWithMessageQueue setPerson(Person person) {
        this.person = person;
        return this;
    }
}
