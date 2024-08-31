package exceptions;

public class AlreadyCompletedException extends HimException {
    public AlreadyCompletedException() {
        super("the task has already been completed");
    }
}
