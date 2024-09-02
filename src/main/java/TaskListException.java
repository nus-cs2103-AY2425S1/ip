public class TaskListException extends ToMoException {
    TaskListException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "TaskListException: " + getMessage();
    }
}
