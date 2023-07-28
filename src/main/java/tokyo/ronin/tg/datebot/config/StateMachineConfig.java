package tokyo.ronin.tg.datebot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tokyo.ronin.tg.datebot.contoller.PersonStatus;
import tokyo.ronin.tg.datebot.statemachine.person.PersonStateMachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class StateMachineConfig {
    private final List<PersonStateMachine> machines;

    public StateMachineConfig(List<PersonStateMachine> machines) {
        this.machines = machines;
    }

    @Bean
    public Map<PersonStatus, PersonStateMachine> personStateMachineToStatus() {
        Map<PersonStatus, PersonStateMachine> machinesMap = new HashMap<>();
        machines.forEach(controller -> machinesMap.put(controller.status(), controller));
        return machinesMap;
    }
}