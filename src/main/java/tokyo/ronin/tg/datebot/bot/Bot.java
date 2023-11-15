package tokyo.ronin.tg.datebot.bot;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.contoller.StatusController;
import tokyo.ronin.tg.datebot.entity.UserEntity;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.resource.BotResource;
import tokyo.ronin.tg.datebot.service.UserService;

import java.util.Map;
import java.util.Queue;

@Component
public class Bot extends TelegramLongPollingBot {

    final private BotResource botResource;
    final private UserService userService;

    @Resource
    private Map<UserStatus, StatusController> controllersMap;

    @Autowired
    public Bot(BotResource config, UserService userService) {
        this.botResource = config;
        this.userService = userService;
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
        UserEntity userEntity = userService.getOrCreate(update.getMessage().getChatId());

        PersonWithMessageQueue personWithMessageQueue = new PersonWithMessageQueue(userEntity);

        if (controllersMap.get(userEntity.getStatus()).handle(update, personWithMessageQueue)) {
            userService.update(personWithMessageQueue.getPerson());
        }

        execute(personWithMessageQueue.getMessages());
    }

    private void execute(Queue<BotApiMethod<?>> methodsQueue) {
        for (BotApiMethod<?> message : methodsQueue) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
