package exceptions;

/**
 * Represents exceptions thrown by the chatbot.
 * Thrown when priority specified is not HIGH, MEDIUM or LOW.
 */
public class InvalidPriorityException extends AliceException {
    protected String message;

    /**
     * Initialises a new InvalidPriorityException.
     *
     * @param message the invalid priority.
     */
    public InvalidPriorityException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return message that task does not exist.
     */
    @Override
    public String toString() {
        return String.format("Priority %s does not exist!", message);
    }
}
