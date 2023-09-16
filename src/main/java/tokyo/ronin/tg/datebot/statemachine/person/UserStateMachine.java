package tokyo.ronin.tg.datebot.statemachine.person;

import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.entity.UserEntity;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;

import java.util.List;


public interface UserStateMachine {

    List<UserStatus> transitionStatuses();

    UserStatus status();

    default boolean transition(PersonWithMessageQueue personWithMessageQueue) {
        UserEntity userEntity = personWithMessageQueue.getPerson();
        if (transitionStatuses() != null && !transitionStatuses().contains(userEntity.getStatus())) {
            return false;
        }

        userEntity.setStatus(status());
        afterEntryAction(personWithMessageQueue);
        return true;
    }

    void afterEntryAction(PersonWithMessageQueue personWithMessageQueue);
}