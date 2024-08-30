package snowy;

/**
 * Represents an exception thrown for errors related to Snowy.
 */
public class SnowyException extends RuntimeException {

    /**
     * Creates a new instance of the SnowyException with the specified error message.
     * @param message the error message that caused the exception.
     */
    public SnowyException(String message) {
        super(message);
    }
}
