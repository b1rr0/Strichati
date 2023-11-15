package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import tokyo.ronin.tg.datebot.constant.Language;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.entity.UserEntity;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.statemachine.person.UserStateMachineService;

@Service
public class LanguageController implements StatusController {
    private final UserStateMachineService stateMachine;

    public LanguageController(UserStateMachineService stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public UserStatus status() {
        return UserStatus.LANGUAGE;
    }

    @Override
    public boolean handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        UserEntity userEntity = personWithMessageQueue.getPerson();
        Optional<Language> language = Language.getByFlag(update.getMessage().getText());

        if (language.isEmpty()) {
            return false;
        }

        userEntity.setLanguage(language.get());
        return stateMachine.transition(personWithMessageQueue, UserStatus.DEFAULT);
    }
}
