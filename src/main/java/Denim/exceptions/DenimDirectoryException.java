package denim.exceptions;

/**
 * Represents an exception specific to reading/writing files in the Denim application.
 * This exception is thrown when an error occurs that is related to Denim's file IO operation.
 */
public class DenimDirectoryException extends DenimException {
    public DenimDirectoryException(String message) {
        super(message);
    }
}
