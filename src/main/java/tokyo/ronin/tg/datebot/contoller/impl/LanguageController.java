package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.constant.Language;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.service.SenderService;
import tokyo.ronin.tg.datebot.statemachine.person.PersonStateMachineService;

@Service
public class LanguageController implements StatusController {
    private final PersonStateMachineService stateMachine;
    private final SenderService senderService;

    public LanguageController(PersonStateMachineService stateMachine, SenderService senderService) {
        this.stateMachine = stateMachine;
        this.senderService = senderService;
    }

    @Override
    public PersonStatus status() {
        return PersonStatus.LANGUAGE;
    }

    @Override
    public boolean handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        Person person = personWithMessageQueue.getPerson();
        Language language = Language.getByData(update.getMessage().getText());

        if (language == null) {
            return false;
        }

        person.setLanguage(language);
        return   stateMachine.transition(personWithMessageQueue, PersonStatus.DEFAULT);
    }
}
