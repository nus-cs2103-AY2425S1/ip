package fanny;

/**
 * Represents an exception unique to the Fanny application.
 */
public class FannyException extends Exception {

    /**
     * Constructs a new FannyException with the specified error message.
     *
     * @param message The error message.
     */
    public FannyException(String message) {
        super(message);
    }
}
