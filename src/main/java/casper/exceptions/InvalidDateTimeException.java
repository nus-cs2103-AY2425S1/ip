package casper.exceptions;

/**
 * Thrown to indicate that the provided date-time format is invalid.
 * This exception provides a suggested solution for the correct date-time format.
 */
public class InvalidDateTimeException extends ExceptionWithSolution {
    /**
     * Constructs a new {@code InvalidDateTimeException} with a default message and solution.
     * The exception message indicates that the date-time format is invalid, and the solution
     * provides the correct date-time format for the user to follow.
     */
    public InvalidDateTimeException() {
        super("Invalid date-time format.",
                "Please provide the date-time as 'dd-MM-yyyy HH:mm'. (E.g., 29-08-2024 15:30)");
    }
}
