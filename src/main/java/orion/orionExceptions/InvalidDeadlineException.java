package orion.orionExceptions;

public class InvalidDeadlineException extends OrionException {
    public InvalidDeadlineException(String input) {
        super("Your input was '" + input + "'. However, the correct format for a deadline is: deadline <description> /by <due date>");
    }
}