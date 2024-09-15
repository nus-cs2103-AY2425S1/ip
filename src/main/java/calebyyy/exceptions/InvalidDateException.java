package calebyyy.exceptions;

/**
 * Represents an exception thrown when the date is invalid.
 */
public class InvalidDateException extends CalebyyyException {

    /**
     * Constructor for InvalidDateException.
     */
    public InvalidDateException() {
        super("Brother ur date needs to be in the format d/M/yyyy HHmm!!!!");
    }
    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}

