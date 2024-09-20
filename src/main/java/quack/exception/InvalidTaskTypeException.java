package quack.exception;

/**
 * This exception class indicates that the task type keyed in by the user is not supported by Quack.
 */
public class InvalidTaskTypeException extends Exception {

    /**
     * Creates the InvalidTaskException exception object.
     * @param taskType The task type keyed in by the user.
     */
    public InvalidTaskTypeException(String taskType) {

        super("Im sorry the task type " + taskType + " does not exist. These are the available tasks types : "
            + "\n 1. ToDo \n 2. Deadline \n 3. Event");
    }
}
