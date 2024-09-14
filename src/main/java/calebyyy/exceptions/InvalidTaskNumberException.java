package calebyyy.exceptions;

/**
 * Represents an exception thrown when the task number is invalid.
 */
public class InvalidTaskNumberException extends CalebyyyException {
    /**
     * Constructor for InvalidTaskNumberException.
     */
    public InvalidTaskNumberException() {
        super("Brother ur task number is invalid!!!");
    }

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}
