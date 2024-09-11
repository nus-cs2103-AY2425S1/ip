package duke;
/**
 * Represents exception encoutered by Duck chatbot.
 */
public class DuckException extends Exception {
    public DuckException(String message) {
        super(message);
    }
}