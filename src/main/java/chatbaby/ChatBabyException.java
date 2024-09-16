package chatbaby;

/**
 * Represents an exception specific to the ChatBaby application.
 * This exception is thrown when an error occurs during the execution
 * of the ChatBaby program.
 */
public class ChatBabyException extends Exception {

    /**
     * Constructs a new ChatBabyException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public ChatBabyException(String message) {
        super(message);
    }
}
