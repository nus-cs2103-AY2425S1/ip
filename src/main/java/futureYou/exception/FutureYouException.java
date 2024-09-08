package futureyou.exception;

/**
 * FutureYouException is an exception that is thrown when an error occurs.
 */
public class FutureYouException extends Exception {
    /**
     * Constructs an FutureYouException
     * The message can be specified to describe the error.
     *
     * @param message The message describing the error.
     */
    public FutureYouException(String message) {
        super(message);
    }
}
