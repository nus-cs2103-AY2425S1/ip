package xizi.chatbot;

/**
 * Represents a custom exception for the Xizi application.
 * This exception is thrown to indicate errors or issues related to user commands and application logic.
 */
public class XiziException extends Exception {
    /**
     * Constructs a new {@code XiziException} with the specified detail message.
     *
     * @param message The detail message to be associated with this exception.
     */
    public XiziException(String message) {
        super(message);
    }

}
