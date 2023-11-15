package tokyo.ronin.tg.datebot.statemachine.person.impl;

import org.springframework.stereotype.Component;

import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.SenderService;
import tokyo.ronin.tg.datebot.statemachine.person.UserStateMachine;

import java.util.List;

@Component
public class UserStateMachineBiography implements UserStateMachine {
    private final SenderService senderService;

    public UserStateMachineBiography(SenderService senderService) {
        this.senderService = senderService;
    }

    @Override
    public List<UserStatus> transitionStatuses() {
        return List.of(UserStatus.CV);
    }

    @Override
    public UserStatus status() {
        return UserStatus.BIOGRAPHY;
    }

    @Override
    public void afterEntryAction(PersonWithMessageQueue personWithMessageQueue) {
        if (personWithMessageQueue.getPerson()
                .getLinkTelegraph() != null) {
            senderService.addMessageToQueue(personWithMessageQueue, personWithMessageQueue.getPerson()
                    .getLinkTelegraph());
        } else {
            senderService.addMessageToQueue(personWithMessageQueue, ";3");
        }
    }
}