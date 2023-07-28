package tokyo.ronin.tg.datebot.statemachine.person.impl;

import org.springframework.stereotype.Component;
import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.SenderService;
import tokyo.ronin.tg.datebot.statemachine.person.PersonStateMachine;

import java.util.List;

@Component
public class PersonStateMachineLanguage implements PersonStateMachine {
    private final SenderService senderService;

    public PersonStateMachineLanguage(SenderService senderService) {
        this.senderService = senderService;
    }

    @Override
    public List<PersonStatus> transitionStatuses() {
        return List.of(PersonStatus.DEFAULT);
    }

    @Override
    public PersonStatus status() {
        return PersonStatus.LANGUAGE;
    }

    @Override
    public void entryAction(PersonWithMessageQueue personWithMessageQueue) {
        senderService.addMessageToQueue(personWithMessageQueue, "Выберете язык");
    }
}