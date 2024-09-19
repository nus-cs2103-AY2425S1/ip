package devon;

/**
 * Exception thrown when a task number is invalid or not found in the Devon application.
 * This exception is a specific type of {@link DevonException} used to indicate errors
 * related to task number handling.
 */
public class DevonInvalidTaskNumberException extends DevonException {

    /**
     * Returns a detailed string representation of the exception, including the specific
     * error message related to invalid task numbers.
     *
     * @return A string indicating the error: "OOPS!!! Task not found!".
     */
    @Override
    public String toString() {
        return super.toString() + " Task not found!";
    }
}
