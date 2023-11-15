package tokyo.ronin.tg.datebot.contoller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.entity.BiographyEntity;
import tokyo.ronin.tg.datebot.mappers.BiographyMapper;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.models.UserData;
import tokyo.ronin.tg.datebot.service.LocalizationService;
import tokyo.ronin.tg.datebot.service.SenderService;
import tokyo.ronin.tg.datebot.service.TelegraphService;
import tokyo.ronin.tg.datebot.statemachine.person.UserStateMachineService;

@Component
public class BioController implements StatusController {
    private final UserStateMachineService stateMachine;
    private final TelegraphService telegraphService;
    private final SenderService senderService;

    public BioController(
            UserStateMachineService stateMachine, TelegraphService telegraphService, SenderService senderService) {
        this.stateMachine = stateMachine;
        this.telegraphService = telegraphService;
        this.senderService = senderService;
    }

    @Override
    public UserStatus status() {
        return UserStatus.BIOGRAPHY;
    }

    @Override
    public boolean handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        if (isWebAppDataMessage(update)) {
            UserData user = extractUserDataFromMessage(update);
            if (user != null) {
                BiographyEntity biography = BiographyMapper.toBiography(user);
                personWithMessageQueue.getPerson()
                        .setBiography(biography);
                telegraphService.createOrUpdateTelegraph(personWithMessageQueue);

                String telegraph = personWithMessageQueue.getPerson()
                        .getLinkTelegraph();
                senderService.addMessageToQueue(personWithMessageQueue, telegraph);

                return stateMachine.transition(personWithMessageQueue, UserStatus.DEFAULT);
            }
        } else if (isReturnCommand(update, personWithMessageQueue)) {
            return stateMachine.transition(personWithMessageQueue, UserStatus.DEFAULT);
        }
        return false;
    }

    private boolean isWebAppDataMessage(Update update) {
        return update.getMessage() != null && update.getMessage()
                .getWebAppData() != null;
    }

    private UserData extractUserDataFromMessage(Update update) {
        String data = update.getMessage()
                .getWebAppData()
                .getData();
        try {
            return new ObjectMapper().readValue(data, UserData.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private boolean isReturnCommand(Update update, PersonWithMessageQueue personWithMessageQueue) {
        return update.getMessage()
                .getText()
                .equals(LocalizationService.getData(personWithMessageQueue.getPerson()
                        .getLanguage(), "return"));
    }
}