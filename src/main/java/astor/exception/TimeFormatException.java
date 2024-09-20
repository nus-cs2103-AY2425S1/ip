package astor.exception;

public class TimeFormatException extends AstorException {
    public TimeFormatException() {
        super("Please provide a valid time format:\n"
                + "dd/mm/yyyy hhmm\n"
                + "e.g. 12/12/2012 1800 or 15/10/2025");
    }
}
