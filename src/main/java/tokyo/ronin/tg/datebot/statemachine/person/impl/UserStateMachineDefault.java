package tokyo.ronin.tg.datebot.statemachine.person.impl;

import org.springframework.stereotype.Component;
import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.LocalizationService;
import tokyo.ronin.tg.datebot.service.SenderService;
import tokyo.ronin.tg.datebot.statemachine.person.UserStateMachine;

import java.util.Arrays;
import java.util.List;

@Component
public class UserStateMachineDefault implements UserStateMachine {
    private final SenderService senderService;

    public UserStateMachineDefault(SenderService senderService) {
        this.senderService = senderService;
    }

    @Override
    public List<UserStatus> transitionStatuses() {
        return Arrays.stream(UserStatus.values()).toList();
    }

    @Override
    public UserStatus status() {
        return UserStatus.DEFAULT;
    }

    @Override
    public void afterEntryAction(PersonWithMessageQueue personWithMessageQueue) {
        senderService.addMessageToQueue(personWithMessageQueue,
                LocalizationService.getData(personWithMessageQueue.getPerson().getLanguage(), "defaultPage"));
    }
}