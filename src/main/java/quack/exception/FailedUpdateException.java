package quack.exception;

import quack.tasks.Task;

/**
 * This exception class indicates that the task did not get updated.
 */
public class FailedUpdateException extends Exception {

    /**
     * Creates an FailedUpdateException exception object.
     * @param task The task object to be updated.
     * @param command The command which the user entered.
     */
    public FailedUpdateException(Task task, String command) {

        super("I'm sorry but this task " + task.toString() + " has already been " + command + "ed!");
    }
}
