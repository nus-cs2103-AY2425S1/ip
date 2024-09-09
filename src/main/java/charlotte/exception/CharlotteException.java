package charlotte.exception;

/**
 * The CharlotteException class represents exceptions specific to the Charlotte chatbot.
 * It extends the Exception class to provide custom error handling for issues that may arise within the app.
 */
public class CharlotteException extends Exception {
    public CharlotteException(String message) {
        super(message);
    }
}
