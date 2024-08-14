package sigma.exception;

/**
 * Represents an exception thrown when the end date is earlier than the start date in a date range.
 * This exception is typically used in contexts where a valid date range is required.
 */
public class SigmaInvalidDateRangeException extends SigmaException {
    /**
     * Returns a string representation of this exception, indicating that the end date
     * cannot be earlier than the start date.
     *
     * @return a string representation of this exception with an appropriate error message
     */
    @Override
    public String toString() {
        return String.format("%s End date cannot be earlier than start date.", super.toString());
    }

}
