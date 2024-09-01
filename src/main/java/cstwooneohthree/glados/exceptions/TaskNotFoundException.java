package cstwooneohthree.glados.exceptions;

/**
 * TaskNotFoundException class when task does not exist.
 *
 * @author jayjay19630
 */
public class TaskNotFoundException extends GladosException {
    /**
     * Constructs TaskNotFoundException by calling the parent class.
     */
    public TaskNotFoundException() {
        super("Task does not exist.");
    }
}
