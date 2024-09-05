package vuewee.parser;

import vuewee.task.Task;

/**
 * Exception thrown when an illegal command is encountered.
 */
public class IllegalCommandException extends IllegalArgumentException {

    /**
     * Constructs a new IllegalCommandException with no detail message.
     */
    public IllegalCommandException() {
        super();
    }

    /**
     * Constructs a new IllegalCommandException with the specified detail message.
     *
     * @param message the detail message
     */
    public IllegalCommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new IllegalCommandException with the specified task and marked
     * status.
     *
     * @param task   the task causing the exception
     * @param isDone the marked status of the task
     */
    public IllegalCommandException(Task task, boolean isDone) {
        super((isDone ? "This task is already done:\n  " : "This task is already marked as not done:\n  ")
                + task.toString());
    }
}
