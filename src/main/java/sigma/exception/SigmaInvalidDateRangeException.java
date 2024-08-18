package sigma.exception;

/**
 * The {@code SigmaInvalidDateRangeException} class is a custom exception that extends
 * the {@code SigmaException} class. This exception is thrown when an invalid date range
 * is encountered, specifically when the end date is earlier than the start date.
 *
 * <p>This exception provides a clear error message indicating that the end date cannot
 * be earlier than the start date, helping users to correct the date range in their input.</p>
 *
 * @see sigma.exception.SigmaException
 */
public class SigmaInvalidDateRangeException extends SigmaException {
    /**
     * Returns a string representation of this exception, providing a detailed message
     * that explains the invalid date range.
     *
     * <p>The message indicates that the end date cannot be earlier than the start date,
     * guiding users to correct the input.</p>
     *
     * @return a string representation of this {@code SigmaInvalidDateRangeException}
     */
    @Override
    public String toString() {
        return String.format("%s End date cannot be earlier than start date.", super.toString());
    }

}
