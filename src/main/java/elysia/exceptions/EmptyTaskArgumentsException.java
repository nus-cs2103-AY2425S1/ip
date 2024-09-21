package elysia.exceptions;

/**
 * Custom exception class to handle cases where no arguments are provided for a task-related command
 * in the Elysia application. Extends the {@code ElysiaException} class.
 */
public class EmptyTaskArgumentsException extends ElysiaException {

    private String details;

    /**
     * Constructs a new {@code EmptyTaskArgumentsException} with a specified task event type.
     * The exception message indicates that no arguments were provided for the task command.
     *
     * @param eventType the type of task event for which no arguments were provided.
     */
    public EmptyTaskArgumentsException(String eventType) {
        super("No arguments found for the task command.");
        this.details = eventType;
    }

    /**
     * Returns the details about the task event type that triggered the exception due to missing arguments.
     *
     * @return a string representing the task event type that lacked the expected arguments.
     */
    public String getDetails() {
        return details;
    }
}
