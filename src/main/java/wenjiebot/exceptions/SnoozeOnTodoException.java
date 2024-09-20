package wenjiebot.exceptions;

/**
 * Represents an exception thrown when an attempt is made to snooze a task
 * of type "ToDo," which does not have a date/time associated with it.
 * This exception provides a custom error message to inform the user
 * that snoozing cannot be applied to a ToDo task.
 */
public class SnoozeOnTodoException extends WenJieException {

    /**
     * Constructs a SnoozeOnTodoException with a default error message.
     * This message indicates that snoozing a ToDo task is not allowed.
     */
    public SnoozeOnTodoException() {
        super("test");
    }

    /**
     * Returns a custom error message indicating that a ToDo task cannot be snoozed
     * because it lacks a date/time.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "Nyaa~~ You can't snooze a todo task! It doesn't have a date time silly goose!";
    }
}
