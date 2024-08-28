package yapper.exceptions;

public class TaskLimitException extends YapperException {
    public TaskLimitException() {
        super("yapper.main.Task list at full capacity");
    }
}
