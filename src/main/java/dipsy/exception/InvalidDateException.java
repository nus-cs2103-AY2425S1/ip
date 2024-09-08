package dipsy.exception;

/**
 * Represents an exception that is thrown when an invalid date format is encountered.
 * This exception is used to notify the user that the date format provided is incorrect.
 */
public class InvalidDateException extends Exception {
    /**
     * Returns a detailed error message indicating that the date format is invalid.
     *
     * @return The error message specifying the correct date format.
     */
    @Override
    public String getMessage() {
        return "Invalid date format. Please enter the date in the format yyyy-MM-dd (e.g., 2024-08-25).";
    }
}
