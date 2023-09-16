package tokyo.ronin.tg.datebot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.contoller.StatusController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ControllersConfig {
    private final List<StatusController> controllers;

    public ControllersConfig(List<StatusController> controllers) {
        this.controllers = controllers;
    }

    @Bean
    public Map<UserStatus, StatusController> controllersMap() {
        Map<UserStatus, StatusController> statusMap = new HashMap<>();
        controllers.forEach(controller -> statusMap.put(controller.status(), controller));
        return statusMap;
    }
}
