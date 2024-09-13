package cancelgpt.exception.task;

/**
 * Represents an Exception for incorrect delete task input.
 */
public class DeleteTaskInputException extends Exception {
    /**
     * Initialises DeleteTaskInputException with message indicating wrong delete task
     * input.
     */
    public DeleteTaskInputException() {
        super("Delete task input must be in the form `delete [integer], where integer is the task number in"
                + " the tasks list");
    }
}
