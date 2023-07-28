package tokyo.ronin.tg.datebot.contoller;

import org.telegram.telegrambots.meta.api.objects.Update;

import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;


public interface StatusController {
    PersonStatus status();
    boolean handle(Update update, PersonWithMessageQueue person);
}
