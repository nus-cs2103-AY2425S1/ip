package devon;

/**
 * Exception thrown when there is an invalid date-time format in the Devon application.
 * This exception is a specific type of {@link DevonException} used to indicate errors
 * related to date-time parsing.
 */
public class DevonInvalidDateTimeException extends DevonException {

    /**
     * Returns a detailed string representation of the exception, including the specific
     * error message related to invalid date-time formats.
     *
     * @return A string indicating the error: "OOPS!!! Invalid date-time format for '/from' or '/to'.
     *         Please use 'yyyy-MM-dd HHmm'."
     */
    @Override
    public String toString() {
        return super.toString() + " Invalid date-time format for '/from' or '/to'. Please use 'yyyy-MM-dd HHmm'.";
    }
}

