package calebyyy.exceptions;

/**
 * Represents an exception thrown by Calebyyy.
 */
public class CalebyyyException extends Exception {
    /**
     * Constructor for CalebyyyException.
     *
     * @param message The message to be displayed.
     */
    public CalebyyyException(String message) {
        super("" + message);
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
