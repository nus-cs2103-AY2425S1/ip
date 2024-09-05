package chatsy.exceptions;

/**
 * Represents an exception that is thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends ChatsyException {

    /**
     * Returns a string representation of the error message indicating an invalid command.
     *
     * @return The error message as a string.
     */
    @Override
    public String toString() {
        return "I'm sorry, but I don't know what that means :-(";
    }
}
