package elara.task;

/**
 * Represents an exception that is thrown when the user provides invalid input.
 * This exception is used to signal errors related to user commands or input that the system cannot process.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs a new InvalidInputException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
