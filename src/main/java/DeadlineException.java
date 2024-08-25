public class DeadlineException extends InputException {
    public enum ExceptionType {
    }
    public DeadlineException() {
        super("The deadline format is wrong!");
    }
}
