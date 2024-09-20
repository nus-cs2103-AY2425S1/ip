package astor.exception;

/**
 * Exception thrown when an invalid time format is provided.
 * <p>
 * This exception is specifically used to indicate that the input time does not
 * match the expected format, which is "dd/mm/yyyy hhmm".
 * </p>
 * <p>
 * Example of valid formats:
 * <ul>
 *   <li>12/12/2012 1800</li>
 *   <li>15/10/2025</li>
 * </ul>
 * </p>
 */
public class TimeFormatException extends AstorException {
    /**
     * Constructs a {@code TimeFormatException} with a default error message.
     */
    public TimeFormatException() {
        super("Please provide a valid time format:\n"
                + "dd/mm/yyyy hhmm\n"
                + "e.g. 12/12/2012 1800 or 15/10/2025");
    }
}
