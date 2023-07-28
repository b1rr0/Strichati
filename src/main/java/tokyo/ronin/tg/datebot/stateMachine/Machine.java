package tokyo.ronin.tg.datebot.stateMachine;

import org.springframework.stereotype.Component;

import tokyo.ronin.tg.datebot.contoller.Status;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.SenderService;

@Component
public class Machine {
    private final SenderService senderService;

    public Machine(SenderService senderService) {
        this.senderService = senderService;
    }

    public void entryLocationStatus(PersonWithMessageQueue personWithMessageQueue) {
        Person person = personWithMessageQueue.getPerson();

        if (person.getStatus() != Status.DEFAULT) {
            return;
        }
        person.setStatus(Status.SETTING_LOCATION);
        senderService.addMessageToQueue(personWithMessageQueue, "Напишите город или укажите локацию");
    }
}
