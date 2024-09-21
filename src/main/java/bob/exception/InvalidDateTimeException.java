package bob.exception;

public class InvalidDateTimeException extends BobException {
    public InvalidDateTimeException() {
        super("WHOA! Time machines aren't in my toolkit, so let's stick to a valid format:\n"
                + "DD/MM/YYYY hhmm");
    }
}
