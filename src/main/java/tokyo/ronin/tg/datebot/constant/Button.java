package tokyo.ronin.tg.datebot.constant;

public enum Button {
    SHARE_LOCATION("getLocation");
    private String value;

    Button(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Button setValue(String value) {
        this.value = value;
        return this;
    }
}
