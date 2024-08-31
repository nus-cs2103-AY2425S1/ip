package calebyyy.exceptions;

/**
 * Represents an exception thrown when an invalid argument is provided.
 */
public class InvalidArgumentException extends CalebyyyException {
    /**
     * Constructor for InvalidArgumentException.
     */
    public InvalidArgumentException() {
        super("Your argument can't be empty!!!");
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}

