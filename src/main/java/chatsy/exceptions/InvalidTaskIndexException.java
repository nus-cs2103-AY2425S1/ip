package chatsy.exceptions;

/**
 * Represents an exception that is thrown when an invalid task index is provided.
 */
public class InvalidTaskIndexException extends ChatsyException {

    /**
     * Returns a string representation of the error message indicating an invalid task index.
     *
     * @return The error message as a string.
     */
    @Override
    public String toString() {
        return "Oops, invalid index provided.\n";
    }
}
