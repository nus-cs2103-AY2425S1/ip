package exception;

/**
 * Exception relates to TaskList
 */
public class TaskListException extends ToMoException {
    /**
     * Constructor of the TaskListException
     * 
     * @param message The error message
     */
    public TaskListException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "TaskListException: " + getMessage();
    }
}
