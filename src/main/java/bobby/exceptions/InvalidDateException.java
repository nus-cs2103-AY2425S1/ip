package bobby.exceptions;

/**
 * The {@code InvalidDateException} class represents a specific type of {@code BobbyException}
 * that is thrown when a date provided by the user is in an incorrect format.
 */
public class InvalidDateException extends BobbyException {

    /**
     * Constructs a new {@code InvalidDateException} with a default detail message
     * indicating that the date format is invalid and should be in the format "yyyy-MM-dd".
     */
    public InvalidDateException() {
        super("Invalid date format. Please use yyyy-MM-dd.");
    }
}
