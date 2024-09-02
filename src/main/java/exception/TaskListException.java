package exception;

public class TaskListException extends ToMoException {
    public TaskListException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "TaskListException: " + getMessage();
    }
}
