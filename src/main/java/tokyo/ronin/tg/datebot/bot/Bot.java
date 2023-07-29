package tokyo.ronin.tg.datebot.bot;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.repository.UserRepository;
import tokyo.ronin.tg.datebot.resource.BotResource;

import java.util.Map;

@Component
public class Bot extends TelegramLongPollingBot {

    final private BotResource botResource;
    final private UserRepository userRepository;

    @Resource
    Map<PersonStatus, StatusController> controllerNameToControllerMap;

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
        boolean isPositive = controllerNameToControllerMap.get(person.getStatus())
                .handle(update, personWithMessageQueue);

        for (BotApiMethod<?> message : personWithMessageQueue.getMessages()) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (isPositive) userRepository.update(personWithMessageQueue.getPerson());
        System.out.println(personWithMessageQueue);
    }
}
