package calebyyy.exceptions;

/**
 * Represents an exception thrown when an invalid command is provided.
 */
public class InvalidCommandException extends CalebyyyException {
    /**
     * Constructor for InvalidCommandException.
     */
    public InvalidCommandException() {
        super("Brother ur command is nonsense!!!");
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

