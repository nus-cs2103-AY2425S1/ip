public class TaskLimitException extends YapperException {
    public TaskLimitException() {
        super("Task list at full capacity");
    }
}
