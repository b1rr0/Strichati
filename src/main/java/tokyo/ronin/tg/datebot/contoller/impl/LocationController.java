package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.entity.UserEntity;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.external.LocationService;
import tokyo.ronin.tg.datebot.statemachine.person.UserStateMachineService;

@Service
public class LocationController implements StatusController {
    private final LocationService locationService;
    private final UserStateMachineService stateMachine;

    @Autowired
    public LocationController(LocationService locationService, UserStateMachineService stateMachine) {
        this.locationService = locationService;
        this.stateMachine = stateMachine;
    }

    @Override
    public UserStatus status() {
        return UserStatus.SETTING_LOCATION;
    }

    @Override
    public boolean handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        UserEntity userEntity = personWithMessageQueue.getPerson();
        Message msg = update.getMessage();

        if (msg.getLocation() != null) {
            locationService.setLocationToPerson(userEntity, msg.getLocation());
        } else {
            locationService.setLocationToPerson(userEntity, msg.getText());
        }

        return stateMachine.transition(personWithMessageQueue, UserStatus.DEFAULT);
    }
}
