package elara.task;

/**
 * Represents an exception that is thrown when the user provides invalid input for a deadline task.
 */
public class DeadlineException extends InvalidInputException {

    /**
     * Constructs a new DeadlineException with a predefined error message.
     * The message specifies the correct input format for a deadline task and provides an example
     * of the expected date format.
     */
    public DeadlineException() {
        super("Your input format is invalid. Please use: 'deadline <description> /by <actual deadline>' \n"
                + "<actual deadline> format: yyyy-MM-dd HHmm");
    }
}
