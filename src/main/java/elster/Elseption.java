package elster;

/**
 * Custom Elseption Error for the Elster chatbot.
 */
public class Elseption extends Exception {
    public Elseption() {
        super("Elseption thrown");
    }

    public Elseption(String message) {
        super(message);
    }
}

