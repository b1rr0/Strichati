package tokyo.ronin.tg.datebot.statemachine.person;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;

import java.util.Map;

@Component
public class PersonStateMachineService {
    @Resource
    private Map<PersonStatus, PersonStateMachine> personStateMachineToStatus;

    public boolean transition(PersonWithMessageQueue personWithMessageQueue, PersonStatus status) {
        return personStateMachineToStatus.get(status).transition(personWithMessageQueue);
    }
}