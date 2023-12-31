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
        if (update.getMessage().getText() == null) {
            return false;
        }

        String msg = update.getMessage().getText();
        return switch (msg) {
            case "1" -> stateMachine.transition(personWithMessageQueue, UserStatus.DEFAULT);
            case "2" -> stateMachine.transition(personWithMessageQueue, UserStatus.CV);
            case "3" -> stateMachine.transition(personWithMessageQueue, UserStatus.LANGUAGE);
            case "4" -> stateMachine.transition(personWithMessageQueue, UserStatus.DEFAULT);
            default -> false;
        };
    }
    //defaultPage=1. View profiles.\n2. My profile.\n3. Not searching anymore.\n***\n4. Invite friends to get more
    // likes ð.
}
