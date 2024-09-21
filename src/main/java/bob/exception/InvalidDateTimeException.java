package bob.exception;

/**
 * Thrown when datetime input by user does not follow the required format: "d[d]/M[M][/uuuu][ HHmm]"
 */
public class InvalidDateTimeException extends BobException {
    public InvalidDateTimeException() {
        super("WHOA! Time machines aren't in my toolkit, so let's stick to a valid format:\n" +
                "DD/MM/YYYY hhmm");
    }
}
