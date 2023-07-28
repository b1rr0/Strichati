package tokyo.ronin.tg.datebot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.entity.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.repository.UserRepository;
import tokyo.ronin.tg.datebot.resource.BotResource;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.contoller.Status;

@Component
public class Bot extends TelegramLongPollingBot {

    final private BotResource botResource;
    final private UserRepository userRepository;

    @Resource
    Map<Status, StatusController> controllerNameToControllerMap;

    @Autowired
    public Bot(BotResource config, UserRepository userRepository) {
        this.botResource = config;
        this.userRepository = userRepository;
    }

    @Override
    public String getBotUsername() {
        return botResource.getName();
    }

    @Override
    public String getBotToken() {
        return botResource.getToken();
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        Person person = userRepository.getUserById(update.getMessage()
                .getChatId());

        PersonWithMessageQueue personWithMessageQueue = new PersonWithMessageQueue(person);
        controllerNameToControllerMap.get(person.getStatus())
                .handle(update, personWithMessageQueue);


        for (BotApiMethodMessage message : personWithMessageQueue.getMessages()) {
            try {
                execute(message);
                userRepository.update(personWithMessageQueue.getPerson());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
