package denim.exceptions;

/**
 * Represents an exception specific to reading/writing files in the Denim application.
 * This exception is thrown when an error occurs that is related to Denim's file IO operation.
 */
public class DenimFileException extends DenimException {

    public DenimFileException(String message) {
        super(message);
    }
}
