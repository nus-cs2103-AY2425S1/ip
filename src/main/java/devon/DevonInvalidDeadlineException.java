package devon;

/**
 * Exception thrown when there is an invalid deadline format in the Devon application.
 * This exception is a specific type of {@link DevonException} used to indicate errors
 * related to deadline parsing.
 */
public class DevonInvalidDeadlineException extends DevonException {

    /**
     * Returns a detailed string representation of the exception, including the specific
     * error message related to invalid deadline formats.
     *
     * @return A string indicating the error: "OOPS!!! Deadline is invalid! Usage: deadline [task] /by [deadline]".
     */
    @Override
    public String toString() {
        return super.toString() + " Deadline is invalid! Usage: deadline [task] /by [deadline]";
    }
}
