package tokyo.ronin.tg.datebot.statemachine.person;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;

import java.util.Map;

@Component
public class UserStateMachineService {
    @Resource
    private Map<UserStatus, UserStateMachine> personStateMachineToStatus;

    public boolean transition(PersonWithMessageQueue personWithMessageQueue, UserStatus status) {
        return personStateMachineToStatus.get(status).transition(personWithMessageQueue);
    }
}