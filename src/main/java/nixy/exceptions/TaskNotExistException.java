package nixy.exceptions;
/**
 * TaskNotExistException is a runtime exception
 * thrown when an indicated task does not exist in the task list.
 */
public class TaskNotExistException extends NixyException {
    public TaskNotExistException(String message) {
        super(message);
    }
}
