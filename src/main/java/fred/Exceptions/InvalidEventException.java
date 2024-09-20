package fred.Exceptions;

public class InvalidEventException extends FredException {
    public InvalidEventException() {
        super("OOPS!!! Please use this format: event orientation day /from 2000-01-01 00:00 /to 2000-01-02 00:00");
    }
}
