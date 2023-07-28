package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.LocationService;
import tokyo.ronin.tg.datebot.statemachine.person.PersonStateMachineService;

@Service
public class LocationController implements StatusController {
    private final LocationService locationService;
    private final PersonStateMachineService stateMachine;

    @Autowired
    public LocationController(LocationService locationService, PersonStateMachineService stateMachine) {
        this.locationService = locationService;
        this.stateMachine = stateMachine;
    }

    @Override
    public PersonStatus status() {
        return PersonStatus.SETTING_LOCATION;
    }

    @Override
    public boolean handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        Person person = personWithMessageQueue.getPerson();
        Message msg = update.getMessage();

        if (msg.getLocation() != null) {
            locationService.setLocationToPerson(person, msg.getLocation());
        } else {
            locationService.setLocationToPerson(person, msg.getText());
        }

        return    stateMachine.transition(personWithMessageQueue, PersonStatus.DEFAULT);

    }
}
