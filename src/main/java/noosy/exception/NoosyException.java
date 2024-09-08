package noosy.exception;

/**
 * Represents a custom exception for the Noosy task management system.
 * This exception is thrown when specific errors occur within the Noosy application.
 */
public class NoosyException extends Throwable {

    /**
     * Constructs a new NoosyException with the specified error message.
     *
     * @param errorMessage The detail message for this exception.
     */
    public NoosyException(String errorMessage) {
        super(errorMessage);
    }
}
