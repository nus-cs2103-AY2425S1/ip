package chatsy.exceptions;

/**
 * Represents an exception that is thrown when a task string is in an invalid format.
 */
public class InvalidTaskStringException extends ChatsyException {

    /**
     * Returns a string representation of the error message indicating an invalid task string format.
     *
     * @return The error message as a string.
     */
    @Override
    public String toString() {
        return "The task data is in an invalid format.";
    }
}
