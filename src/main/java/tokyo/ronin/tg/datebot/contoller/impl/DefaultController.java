package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.statemachine.person.PersonStateMachineService;

@Component
public class DefaultController implements StatusController {
    private final PersonStateMachineService stateMachine;

    public DefaultController(PersonStateMachineService stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public PersonStatus status() {
        return PersonStatus.DEFAULT;
    }

    @Override
    public boolean handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        if (Objects.equals(update.getMessage().getText(), "bio")) {
            stateMachine.transition(personWithMessageQueue, PersonStatus.BIOGRAPHY);
        }

        if (Objects.equals(update.getMessage().getText(), "lang")) {
            stateMachine.transition(personWithMessageQueue, PersonStatus.LANGUAGE);
        }

        if (Objects.equals(update.getMessage().getText(), "loc")) {
            stateMachine.transition(personWithMessageQueue, PersonStatus.SETTING_LOCATION);
        }
        return true;
    }
}
