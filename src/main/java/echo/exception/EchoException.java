package echo.exception;

/**
 * Represents Exception thrown while using Echo
 *
 * @author Ernest Lim
 */
public class EchoException extends RuntimeException {

    /**
     * Constructor for EchoException object
     *
     * @param errorMessage message to be displayed when the error is caught
     */
    public EchoException(String errorMessage) {
        super(errorMessage);
    }
}
