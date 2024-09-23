package yap.ui;

/**
 * An exception thrown by invalid input to the chatbot.
 */
public class InputException extends Exception {
    public InputException() {
        super("Oops! I'm Sorry, I don't understand this :(\n"
                + "If you need help, use the help command!");
    }
    public InputException(String message) {
        super(message);
    }
}
