package botty.exceptions;

/**
 * Exception thrown when incorrect date format is given
 */
public class IncorrectDateFormatException extends BottyException {
    /**
     * Constructs a new {@code IncorrectDateFormatException} with the given message.
     * @param message the detail message.
     */
    public IncorrectDateFormatException(String message) {
        super(message);
    }
}
