package james;

/**
 * Represents an exception thrown when a required description is missing.
 * <p>
 * This exception is a specific type of JamesException used to indicate
 * that a task description or related information is incomplete or missing.
 * </p>
 */
public class MissingDescriptionException extends JamesException {
    /**
     * Creates a new MissingDescriptionException with the specified message.
     *
     * @param message The detail message of the exception
     */
    public MissingDescriptionException(String message) {
        super(message);
    }
}
