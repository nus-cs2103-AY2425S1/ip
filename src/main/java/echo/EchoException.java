package echo;
/**
 * The EchoException class represents custom exceptions for the Echo application.
 * It extends the standard Java Exception class to provide specific error messages
 * related to tasks and commands within the Echo application.
 */
public class EchoException extends Exception {
    /**
     * Constructs a new EchoException with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public EchoException(String message) {
        super(message);
    }

}
