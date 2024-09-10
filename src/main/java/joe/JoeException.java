package joe;

/**
 * The {@code JoeException} class represents a custom unchecked exception
 * used in the application to handle specific error scenarios related to user input
 * or command parsing.
 */
public class JoeException extends RuntimeException {
    /**
     * Constructs a new {@code JoeException} with the specified detail message.
     *
     * @param msg The detail message that describes the exception.
     */
    public JoeException(String msg) {
        super(msg);
    }
}
