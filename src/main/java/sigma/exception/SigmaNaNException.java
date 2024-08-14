package sigma.exception;

/**
 * Thrown when a non-numeric value is provided where a numeric task number is expected.
 */
public class SigmaNaNException extends SigmaException {
    /**
     * Returns a detailed error message indicating that the task number is not numeric.
     *
     * @return a string representing the error message
     */
    @Override
    public String toString() {
        return String.format("%s Task number is not numeric. Please enter a number", super.toString());
    }
}
