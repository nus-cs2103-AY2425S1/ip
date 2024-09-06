package yap.ui;

public class InputException extends Exception {
    public InputException() {
        super("Oops! I'm Sorry, I don't understand this :(");
    }
    public InputException(String message) {
        super(message);
    }
}
