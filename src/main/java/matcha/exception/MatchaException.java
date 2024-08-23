package matcha.exception;

/**
 * Represents a custom exception for all errors related to the Matcha program
 */
public class MatchaException extends Exception{

    /**
     * Constructs a new MatchaException with the error message
     * @param msg the error message
     */
    public MatchaException(String msg) {
        super(msg);
    }

    /**
     * Returns a string representation of the error message
     *
     * @return the error message
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
