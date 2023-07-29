package tokyo.ronin.tg.datebot.telegraph;

public enum TelegraphMethod {
    CREATE_ACCOUNT("createAccount"),
    CREATE_PAGE("createPage"),
    EDIT_PAGE("editPage");

    TelegraphMethod(String method) {
        this.method = method;
    }

    private String method;

    public String getMethod() {
        return method;
    }
}