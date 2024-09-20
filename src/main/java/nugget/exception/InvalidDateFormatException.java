package nugget.exception;

public class InvalidDateFormatException extends NuggetException {
    public InvalidDateFormatException() {
        super("The date format is incorrect. Please use 'yyyy-MM-dd HHmm'.");
    }
}
