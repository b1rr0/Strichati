package tokyo.ronin.tg.datebot.resource;

public class BotResource {
    private String name;
    private String token;

    public String getName() {
        return name;
    }

    public BotResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getToken() {
        return token;
    }

    public BotResource setToken(String token) {
        this.token = token;
        return this;
    }
}
