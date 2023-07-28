package tokyo.ronin.tg.datebot.contoller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Map<Status, StatusController> controllerNameToControllerMap() {
        Map<Status, StatusController> statusMap = new HashMap<>();
        controllers.forEach(controller -> statusMap.put(controller.status(), controller));
        return statusMap;
    }
}
