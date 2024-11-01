package exception;

/**
 * Represents an invalid parameter exception where the parameters do not matched the provided regular expression.
 */
public class BlitzInvalidParameterRegexException extends BlitzInvalidParameterFormatException {
    /**
     * Constructs a new BlitzInvalidParameterRegexException object with specified description.
     *
     * @param format String format indicating the correct format.
     */
    public BlitzInvalidParameterRegexException(String format) {
        super(format);
    }

    /**
     * Returns a String representation of this object.
     *
     * @return String indicates wrong parameter format.
     */
    @Override
    public String toString() {
        return "Wrong parameter format! " + super.toString();
    }
}
