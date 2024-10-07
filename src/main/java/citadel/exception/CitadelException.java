package citadel.exception;

/**
 * Represents the base class for all exceptions specific to the Citadel application.
 * This class extends {@link Exception} and provides a common message for invalid inputs.
 * <p>
 * Subclasses of {@code CitadelException} should override the {@code toString()} method
 * to provide specific details about the exception.
 * </p>
 */
public abstract class CitadelException extends Exception {

    /** The default message associated with this exception, indicating invalid input. */
    final String message = "Invalid input: ";

    /**
     * Returns a string representation of this exception.
     * <p>
     * This method returns the default message indicating invalid input.
     * Subclasses can override this method to provide more specific messages.
     * </p>
     *
     * @return A string representing the default message for this exception.
     */
    @Override
    public String toString() {
        return this.message;
    }
}
