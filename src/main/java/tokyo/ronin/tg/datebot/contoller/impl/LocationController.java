package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import tokyo.ronin.tg.datebot.contoller.Status;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.LocationService;

@Service
public class LocationController implements StatusController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @Override
    public Status status() {
        return Status.SETTING_LOCATION;
    }

    @Override
    public void handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        Person person = personWithMessageQueue.getPerson();
        Message msg = update.getMessage();

        if (msg.getLocation() != null) {
            locationService.setLocationToPerson(person, msg.getLocation());
        } else {
            locationService.setLocationToPerson(person, msg.getText());
        }
        
        person.setStatus(Status.DEFAULT);
    }
}
