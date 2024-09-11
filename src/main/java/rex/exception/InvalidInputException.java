package rex.exception;

/**
 * The {@code InvalidInputException} class represents an exception that is thrown
 * when the user provides an invalid input in the Rex application.
 * This class serves as the base class for more specific input-related exceptions.
 */
public class InvalidInputException extends Exception {
    /**
     * Constructs a new {@code InvalidInputException} with the specified detail message.
     *
     * @param message The detail message that describes the reason for the exception.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
