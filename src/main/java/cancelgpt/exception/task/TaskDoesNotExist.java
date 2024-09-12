package cancelgpt.exception.task;

/**
 * Represents an Exception for task not existing.
 */
public class TaskDoesNotExist extends Exception {
    /**
     * Initialises TaskDoesNotExist with message indicating the task does not exist.
     */
    public TaskDoesNotExist() {
        super("The task you are trying to access does not exist");
    }
}
