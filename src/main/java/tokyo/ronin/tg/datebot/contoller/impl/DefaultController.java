package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

import tokyo.ronin.tg.datebot.contoller.Status;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.stateMachine.Machine;

@Component
public class DefaultController implements StatusController {
    public DefaultController(Machine machine) {
        this.machine = machine;
    }

    @Override
    public Status status() {
        return Status.DEFAULT;
    }

    private final Machine machine;

    @Override
    public void handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        Person person = personWithMessageQueue.getPerson();
        if (Objects.equals(update.getMessage()
                .getText(), "lang")) {
            person.setStatus(Status.LANGUAGE);
        }
        if (Objects.equals(update.getMessage().getText(), "loc")) {
            machine.entryLocationStatus(personWithMessageQueue);
        }
    }
}
