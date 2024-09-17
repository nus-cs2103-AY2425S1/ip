package swbot;

/**
 * The BuzzException class is a custom exception type used in the application to handle
 * specific error conditions related to the processing of tasks and user input.
 * <p>
 * This exception is thrown when certain user inputs or operations do not conform to the expected format
 * or contain errors, such as incorrect date/time formatting in tasks.
 */
public class BuzzException extends Exception {
    public BuzzException(String message) {
        super(message);
    }
}
