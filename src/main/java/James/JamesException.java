package james;

/**
 * Represents a custom exception for the James application.
 * <p>
 * Used to indicate errors specific to the application's operation.
 * </p>
 */
public class JamesException extends Exception {
    /**
     * Creates a new JamesException with the specified message.
     *
     * @param message The detail message of the exception
     */
    public JamesException(String message) {
        super(message);
    }
}
