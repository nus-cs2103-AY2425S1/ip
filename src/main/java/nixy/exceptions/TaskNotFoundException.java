package nixy.exceptions;
/**
 * TaskNotFoundException is a runtime exception
 * thrown when an indicated task does not exist in the task list.
 */
public class TaskNotFoundException extends NixyException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
