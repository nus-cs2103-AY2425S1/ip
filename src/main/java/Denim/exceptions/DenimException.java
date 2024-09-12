package denim.exceptions;

/**
 * Represents an exception specific to the Denim application.
 * This exception is thrown when an error occurs that is related to Denim's operation.
 */
public class DenimException extends Exception {

    /**
     * Constructs a new DenimException with the specified detail message.
     *
     * @param message The detail message, which provides more information about the error.
     */
    public DenimException(String message) {
        super(message);
    }
}
