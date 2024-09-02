package exception;

/**
 * Represents a generic invalid parameter exception in the Blitz application.
 */
public class BlitzInvalidParameterFormatException extends BlitzException {
    private String format;

    /**
     * Constructs a new BlitzInvalidParameterFormatException object with specified description.
     *
     * @param format String format indicating the correct format.
     */
    public BlitzInvalidParameterFormatException(String format) {
        this.format = format;
    }

    /**
     * Returns a String representation of this object.
     *
     * @return String indicates wrong format.
     */
    @Override
    public String toString() {
        return "Please use this format \"" + format + "\"!";
    }
}
