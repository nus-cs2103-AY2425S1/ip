package xbot;

/**
 * The XBotException class represents custom exceptions specific to the XBot application.
 * It extends the Exception class and is used to handle errors specific to the application's functionality.
 */
public class XBotException extends Exception {

    /**
     * Constructs a new XBotException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public XBotException(String message) {
        super(message);
    }
}