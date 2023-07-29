package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.TelegraphService;
import tokyo.ronin.tg.datebot.statemachine.person.PersonStateMachineService;

@Component
public class BioController implements StatusController {
    private final PersonStateMachineService stateMachine;
    private final TelegraphService telegraphService;

    public BioController(PersonStateMachineService stateMachine, TelegraphService telegraphService) {
        this.stateMachine = stateMachine;
        this.telegraphService = telegraphService;
    }

    @Override
    public PersonStatus status() {
        return PersonStatus.BIOGRAPHY;
    }

    @Override
    public boolean handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        personWithMessageQueue.getPerson().getBiography().setName("Ronin");
        telegraphService.createOrUpdateTelegraph(personWithMessageQueue);
        return stateMachine.transition(personWithMessageQueue, PersonStatus.DEFAULT);
    }
}