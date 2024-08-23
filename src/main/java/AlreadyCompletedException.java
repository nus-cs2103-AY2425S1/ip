public class AlreadyCompletedException extends Exception {
    public AlreadyCompletedException() {
        super("the task has already been completed");
    }
}
