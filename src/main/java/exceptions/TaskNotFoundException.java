package exceptions;

public class TaskNotFoundException extends GladosException {
    /**
     * Constructs TaskNotFoundException by calling the parent class.
     */
    public TaskNotFoundException() {
        super("Task does not exist.");
    }
}