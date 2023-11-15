package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.statemachine.person.UserStateMachineService;

@Service
public class CVController implements StatusController {
    private final UserStateMachineService stateMachine;

    @Autowired
    public CVController(UserStateMachineService stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public UserStatus status() {
        return UserStatus.CV;
    }

    @Override
    public boolean handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        if (update.getMessage().getText() == null) {
            return false;
        }

        String msg = update.getMessage().getText();
        return switch (msg) {
            case "1" -> stateMachine.transition(personWithMessageQueue, UserStatus.PHOTO);
            case "2" -> stateMachine.transition(personWithMessageQueue, UserStatus.BIOGRAPHY);
            case "3" -> stateMachine.transition(personWithMessageQueue, UserStatus.SETTING_LOCATION);
            case "4" -> stateMachine.transition(personWithMessageQueue, UserStatus.DEFAULT);
            default -> false;
        };
    }

    // photo
    // bio
    // location
    // return
}