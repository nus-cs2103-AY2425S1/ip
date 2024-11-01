package exception;

/**
 * Represents an exception where a non-existent command is being used.
 */
public class BlitzCommandDoesNotExistException extends BlitzException {
    /**
     * Returns a String representation of this object.
     *
     * @return String indicates command does not exist.
     */
    @Override
    public String toString() {
        return "Command does not exist!";
    }
}
