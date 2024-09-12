package orion.orionexceptions;

/**
 * Custom exception class representing an error that occurs when an invalid
 * Deadline format is encountered.
 *
 * <p>
 * This exception is thrown when a deadline input does not conform to
 * the expected format.
 * </p>
 *
 * <p>
 * The expected format for a deadline is:
 * <code>deadline &lt;description&gt; /by &lt;due date&gt;</code>.
 * </p>
 *
 * @author Pradyumna
 */
public class InvalidDeadlineException extends OrionException {

    /**
     * Constructs a new InvalidDeadlineException with the specified detail message.
     * The detail message includes the incorrect input provided and specifies
     * the correct format for a deadline.
     *
     * @param input the invalid input that caused the exception. This is included
     *              in the detail message to help identify what was incorrect.
     */
    public InvalidDeadlineException(String input) {
        super("Your input was '" + input
                + "'. However, the correct format for a deadline is: deadline <description> /by <due date>");
    }
}
