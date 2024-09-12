package tira;

/**
 * Represents an exception specific to the Tira chatbot.
 * This exception is thrown when invalid commands or inputs
 * are provided by the user.
 */
public class TiraException extends Exception {
    public TiraException(String message) {
        super(message);
    }
    // will revise the exceptions later
}
