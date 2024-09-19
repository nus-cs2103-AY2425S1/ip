package devon;

/**
 * Exception thrown when there is an invalid event format in the Devon application.
 * This exception is a specific type of {@link DevonException} used to indicate errors
 * related to event parsing.
 */
public class DevonInvalidEventException extends DevonException {

    /**
     * Returns a detailed string representation of the exception, including the specific
     * error message related to invalid event formats.
     *
     * @return A string indicating the error:
     *         "OOPS!!! Event is invalid! Usage: event [task] /from [start_time] /to [end_time]".
     */
    @Override
    public String toString() {
        return super.toString() + " Event is invalid! Usage: event [task] /from [start_time] /to [end_time]";
    }
}
