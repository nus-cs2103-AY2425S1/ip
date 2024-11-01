package exception;

/**
 * Represents a generic exception in the Blitz application.
 */
public class BlitzException extends Exception {
    /**
     * Returns a String representation of this object.
     *
     * @return String indicates something is wrong.
     */
    @Override
    public String toString() {
        return "Something is wrong, please try again!";
    }
}
