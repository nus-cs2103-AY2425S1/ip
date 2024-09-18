package exceptions;

/**
 * The TaskNotFoundtException class represents a specific type of DelphiException
 * that is thrown when the task being searched for with the given keyword does not exist.
 *
 * This is a custom exception that extends {@link DelphiException}.
 *
 * @author jordanchan
 */
public class TaskNotFoundException extends DelphiException {
    /**
     * Constructs a new TaskNotFoundException with a default error message.
     * The error message indicates that the task inputted cannot be found.
     */
    public TaskNotFoundException() {
        super("couldn't find any tasks matching the description!");
    }
}
