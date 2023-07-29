package tokyo.ronin.tg.datebot.statemachine.person.impl;

import org.springframework.stereotype.Component;
import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.SenderService;
import tokyo.ronin.tg.datebot.statemachine.person.PersonStateMachine;

import java.util.Arrays;
import java.util.List;

@Component
public class PersonStateMachineDefault implements PersonStateMachine {
    private final SenderService senderService;

    public PersonStateMachineDefault(SenderService senderService) {
        this.senderService = senderService;
    }

    @Override
    public List<PersonStatus> transitionStatuses() {
        return Arrays.stream(PersonStatus.values()).toList();
    }

    @Override
    public PersonStatus status() {
        return PersonStatus.DEFAULT;
    }

    @Override
    public void entryAction(PersonWithMessageQueue personWithMessageQueue) {
        senderService.addMessageToQueue(personWithMessageQueue, "вы в дефолте");
    }
}