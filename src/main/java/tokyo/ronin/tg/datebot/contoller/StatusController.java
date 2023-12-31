package tokyo.ronin.tg.datebot.contoller;

import org.telegram.telegrambots.meta.api.objects.Update;

import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;


public interface StatusController {
    UserStatus status();
    boolean handle(Update update, PersonWithMessageQueue person);
}
