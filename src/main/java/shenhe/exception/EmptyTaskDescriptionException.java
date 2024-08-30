package shenhe.exception;

/**
 * Represents an exception thrown when a task description is empty.
 * <p>
 * The {@code EmptyTaskDescriptionException} is used to indicate that a command has been given with an
 * empty or missing task description. This exception provides a specific error message to guide the user
 * to provide a valid description for the task.
 * </p>
 */
public class EmptyTaskDescriptionException extends Exception {

    /**
     * Constructs an {@code EmptyTaskDescriptionException} with a default error message.
     * <p>
     * The default error message is: "My dear traveller, the task description cannot be empty. Please give me something specific."
     * </p>
     */
    public EmptyTaskDescriptionException() {
        super("My dear traveller, the task description cannot be empty. Please give me something specific.");
    }
}
