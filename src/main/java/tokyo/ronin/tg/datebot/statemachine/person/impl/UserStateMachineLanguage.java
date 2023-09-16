package tokyo.ronin.tg.datebot.statemachine.person.impl;

import org.springframework.stereotype.Component;
import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.LocalizationService;
import tokyo.ronin.tg.datebot.service.SenderService;
import tokyo.ronin.tg.datebot.statemachine.person.UserStateMachine;

import java.util.List;

@Component
public class UserStateMachineLanguage implements UserStateMachine {
    private final SenderService senderService;

    public UserStateMachineLanguage(SenderService senderService) {
        this.senderService = senderService;
    }

    @Override
    public List<UserStatus> transitionStatuses() {
        return List.of(UserStatus.DEFAULT);
    }

    @Override
    public UserStatus status() {
        return UserStatus.LANGUAGE;
    }

    @Override
    public void afterEntryAction(PersonWithMessageQueue personWithMessageQueue) {
        senderService.addMessageToQueue(personWithMessageQueue,
                LocalizationService.getData(personWithMessageQueue.getPerson().getLanguage(), "chooseLng"));
    }
}