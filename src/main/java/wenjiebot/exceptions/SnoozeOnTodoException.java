package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the required file cannot be found.
 * This exception provides a custom error message to indicate that the user cannot call snooze on a todo task.
 */
public class SnoozeOnTodoException extends WenJieException {

    /**
     * Constructs a NoFileException with a default error message.
     */
    public SnoozeOnTodoException() {
        super("test");
    }

    /**
     * Returns a custom error message that indicates the file is missing.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "Nyaa~~ You can't snooze a todo task! It doesn't have a date time silly goose!";
    }
}
