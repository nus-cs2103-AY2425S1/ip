package chatsy.exceptions;

/**
 * Represents a general exception in the Chatsy application.
 * This class is the base class for all exceptions in Chatsy.
 */
public class ChatsyException extends Exception {

    /**
     * Returns a string representation of the error message.
     *
     * @return The error message as a string.
     */
    @Override
    public String toString() {
        return "Oops, an error has occurred";
    }
}
