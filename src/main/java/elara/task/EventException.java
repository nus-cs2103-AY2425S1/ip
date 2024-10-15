package elara.task;

/**
 * Represents an exception that is thrown when the user provides invalid input for an event task.
 */
public class EventException extends InvalidInputException {

    /**
     * Constructs a new EventException with a predefined error message.
     * The message specifies the correct input format for an event task and provides the expected format
     * for start and end times.
     */
    public EventException() {
        super("Your input format is invalid. Please use: 'event <description> /from <start> /to <end> \n"
                + "<start> and <end> format: yyyy-MM-dd HHmm");
    }
}
