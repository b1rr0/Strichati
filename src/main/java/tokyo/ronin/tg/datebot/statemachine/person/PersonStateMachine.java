package tokyo.ronin.tg.datebot.statemachine.person;

import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;

import java.util.List;


public interface PersonStateMachine {

    List<PersonStatus> transitionStatuses();

    PersonStatus status();

    default boolean transition(PersonWithMessageQueue personWithMessageQueue) {
        Person person = personWithMessageQueue.getPerson();
        if (transitionStatuses() != null && !transitionStatuses().contains(person.getStatus())) {
            return false;
        }

        entryAction(personWithMessageQueue);
        person.setStatus(status());
        return true;
    }

    void entryAction(PersonWithMessageQueue personWithMessageQueue);
}