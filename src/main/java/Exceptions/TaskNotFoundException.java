package Exceptions;

/**
 * The TaskNotFoundtException class represents a specific type of DelphiException
 * that is thrown when the task being searched for does not exist.
 *
 * @author jordanchan
 */
public class TaskNotFoundException extends DelphiException {
    /**
     * Constructs a new TaskNotFoundException with a default error message.
     * The error message indicates that the task inputted cannot be found.
     */
    public TaskNotFoundException() {
        super("please give me a valid task");
    }
}
