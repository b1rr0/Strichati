package tokyo.ronin.tg.datebot.models;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import tokyo.ronin.tg.datebot.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonWithMessageQueue {
    private final List<BotApiMethod<?>> messages = new ArrayList<>();
    private Person person;

    public PersonWithMessageQueue(Person person) {
        this.person = person;
    }

    public List<BotApiMethod<?>> getMessages() {
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

    @Override
    public String toString() {
        return "PersonWithMessageQueue{" +
                "messages=" + messages +
                ", person=" + person +
                '}';
    }
}