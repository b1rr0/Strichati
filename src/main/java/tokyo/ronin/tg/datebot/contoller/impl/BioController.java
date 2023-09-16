package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.TelegraphService;
import tokyo.ronin.tg.datebot.statemachine.person.UserStateMachineService;

@Component
public class BioController implements StatusController {
    private final UserStateMachineService stateMachine;
    private final TelegraphService telegraphService;

    public BioController(UserStateMachineService stateMachine, TelegraphService telegraphService) {
        this.stateMachine = stateMachine;
        this.telegraphService = telegraphService;
    }

    @Override
    public UserStatus status() {
        return UserStatus.BIOGRAPHY;
    }

    @Override
    public boolean handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        personWithMessageQueue.getPerson().getBiography().setName("Ronin");
        telegraphService.createOrUpdateTelegraph(personWithMessageQueue);
        return stateMachine.transition(personWithMessageQueue, UserStatus.DEFAULT);
    }
}