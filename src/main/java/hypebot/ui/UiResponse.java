package hypebot.ui;

public class UiResponse {
    private final String message;
    public UiResponse(String message) {
        this.message = message;
    }

    public String show() {
        return message;
    }
}
