package calebyyy.exceptions;

/**
 * Represents an exception thrown when a duplicate task is added.
 */
public class DuplicateTaskException extends CalebyyyException {
    /**
     * Constructor for DuplicateTaskException.
     */
    public DuplicateTaskException() {
        super("Brother you already have this task!!!");
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
