package devon;

/**
 * Exception thrown when there is an error reading from the database in the Devon application.
 * This exception is a specific type of {@link DevonException} used to indicate issues
 * related to database reading operations.
 */
public class DevonReadDatabaseException extends DevonException {

    /**
     * Returns a detailed string representation of the exception, including the specific
     * error message related to database reading failures.
     *
     * @return A string indicating the error: "OOPS!!! Unable to read database!".
     */
    @Override
    public String toString() {
        return super.toString() + " Unable to read database!";
    }
}
