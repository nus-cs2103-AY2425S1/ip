package diego;

/**
 * Represents an exception specific to the Diego application.
 */
public class DiegoException extends Exception {
    
    /**
     * Constructs a new DiegoException.
     *
     * @param message The error message associated with the exception.
     */
    public DiegoException(String message) {
        super(message);
    }
}
