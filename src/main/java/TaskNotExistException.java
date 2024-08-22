/**
 * TaskNotExistException is a runtime exception
 * thrown when an indicated task does not exist in the task list.
 */
public class TaskNotExistException extends RuntimeException {
    public TaskNotExistException(String message) {
        super(message);
    }
}
