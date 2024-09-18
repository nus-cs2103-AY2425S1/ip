package gopher.exception;

/**
 * Thrown if the given start date is after the given end date
 */
public class InvalidDurationException extends Exception {
    @Override
    public String getMessage() {
        return "Sorry, but I don't understand why your end date is before your start date...\n"
                + "Please try again with a valid date range";
    }
}
