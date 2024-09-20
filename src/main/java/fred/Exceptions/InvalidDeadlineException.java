package fred.Exceptions;

public class InvalidDeadlineException extends FredException {
    public InvalidDeadlineException() {
        super("OOPS!!! Please use this format: deadline return book /by 2000-01-01 00:00");
    }
}
