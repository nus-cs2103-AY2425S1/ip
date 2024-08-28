package cloud.exception;

/**
 * Superclass for all cloud.Cloud related exceptions
 */
public class CloudException extends Exception {
    public CloudException(String message) {
        super(message);
    }
}
