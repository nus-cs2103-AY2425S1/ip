package chatbuddy.exception;

/**
 * Represents exceptions specific to the ChatBuddy application.
 */
public class ChatBuddyException extends Exception {
    /**
     * Constructs a ChatBuddyException with the specified error message.
     *
     * @param message The error message that provides details about the exception.
     */
    public ChatBuddyException(String message) {
        super(message);
    }
}
