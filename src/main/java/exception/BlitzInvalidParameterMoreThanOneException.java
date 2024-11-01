package exception;

/**
 * Represents an invalid parameter exception where more than one parameter was provided.
 */
public class BlitzInvalidParameterMoreThanOneException extends BlitzInvalidParameterFormatException {
    /**
     * Constructs a new BlitzInvalidParameterMoreThanOneException object with specified description.
     *
     * @param format String format indicating the correct format.
     */
    public BlitzInvalidParameterMoreThanOneException(String format) {
        super(format);
    }

    /**
     * Returns a String representation of this object.
     *
     * @return String indicates only one parameter is required.
     */
    @Override
    public String toString() {
        return "Only ONE parameter is required! " + super.toString();
    }
}
