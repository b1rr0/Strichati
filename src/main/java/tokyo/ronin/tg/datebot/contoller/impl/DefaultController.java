package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.statemachine.person.UserStateMachineService;

@Component
public class DefaultController implements StatusController {
    private final UserStateMachineService stateMachine;

    public DefaultController(UserStateMachineService stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public UserStatus status() {
        return UserStatus.DEFAULT;
    }

    @Override
    public boolean handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        if (Objects.equals(update.getMessage().getText(), "bio")) {
            stateMachine.transition(personWithMessageQueue, UserStatus.BIOGRAPHY);
        }

        if (Objects.equals(update.getMessage().getText(), "lang")) {
            stateMachine.transition(personWithMessageQueue, UserStatus.LANGUAGE);
        }

        if (Objects.equals(update.getMessage().getText(), "loc")) {
            stateMachine.transition(personWithMessageQueue, UserStatus.SETTING_LOCATION);
        }
        return true;
    }
}
