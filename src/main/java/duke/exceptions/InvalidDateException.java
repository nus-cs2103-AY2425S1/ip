/**
 * This exception is thrown when the user inputs a invalid date for a task.
 * <p>
 * The {@code InvalidDateException} is a custom exception that extends the {@code Exception} class.
 * It is used to indicate that the user's input for date is not accepted by the program.
 * </p>
 */
package duke.exceptions;

public class InvalidDateException extends Exception {
    public String toString() {
        return "OOPS!!! Please ensure that date format are in dd/MM/yyyy HH:mm or dd/MM/yyyy.";
    }
}
