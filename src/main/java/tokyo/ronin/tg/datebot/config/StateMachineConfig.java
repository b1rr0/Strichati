package tokyo.ronin.tg.datebot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tokyo.ronin.tg.datebot.contoller.UserStatus;
import tokyo.ronin.tg.datebot.statemachine.person.UserStateMachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class StateMachineConfig {
    private final List<UserStateMachine> machines;

    public StateMachineConfig(List<UserStateMachine> machines) {
        this.machines = machines;
    }

    @Bean
    public Map<UserStatus, UserStateMachine> personStateMachineToStatus() {
        Map<UserStatus, UserStateMachine> machinesMap = new HashMap<>();
        machines.forEach(controller -> machinesMap.put(controller.status(), controller));
        return machinesMap;
    }
}