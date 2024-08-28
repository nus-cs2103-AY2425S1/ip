package chatsy.exceptions;

/**
 * Represents an exception that is thrown when a description is empty.
 */
public class EmptyDescriptionException extends ChatsyException {

    /**
     * Returns a string representation of the error message indicating an empty description.
     *
     * @return The error message as a string.
     */
    @Override
    public String toString() {
        return "The description cannot be empty.";
    }
}
