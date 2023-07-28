package tokyo.ronin.tg.datebot.contoller.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import tokyo.ronin.tg.datebot.contoller.Status;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.constant.Language;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;

@Service
public class LanguageController implements StatusController {

    @Override
    public Status status() {
        return Status.LANGUAGE;
    }

    @Override
    public void handle(Update update, PersonWithMessageQueue personWithMessageQueue) {
        Person person = personWithMessageQueue.getPerson();
        var v = Language.valueOf(update.getMessage()
                .getText());

        person.setLanguage(v);
        person.setStatus(Status.DEFAULT);
    }
}
