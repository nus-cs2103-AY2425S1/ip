package exception;
import prince.Prince;

/**
 * Exception raised for when deadline commands are not completed in the correct format
 *
 * This exception is typically raised when a user attempts to execute a deadline task
 * command but did not provide all the necessary details required for the command's deadline.
 *
 * eg. of incomplete commands: deadline submit homework, deadline submit homework /by 12 Dec
 */

public class InvalidDeadlineException extends Exception {
    public InvalidDeadlineException(String message) {
        super(message);
    }
}
