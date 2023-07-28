package tokyo.ronin.tg.datebot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tokyo.ronin.tg.datebot.resource.BotResource;
import tokyo.ronin.tg.datebot.resource.JOpenResource;

@Configuration
public class Config {

    @Bean
    @ConfigurationProperties(prefix = "bot")
    public BotResource botResource() {
        return new BotResource();
    }

    @Bean
    @ConfigurationProperties(prefix = "jopen")
    public JOpenResource jopenResource() {
        return new JOpenResource();
    }
}
