package exception;

/**
 * Represents an exception where no parameters are provided when parameteres are expected.
 */
public class BlitzNoParameterException extends BlitzException {
    /**
     * Returns a String representation of this object.
     *
     * @return String indicates missing parameters.
     */
    @Override
    public String toString() {
        return "Missing parameter(s) for command!";
    }
}
