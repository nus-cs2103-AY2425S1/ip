package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the user provides an invalid input format
 * for the snooze command. This exception provides a custom error message to guide the user
 * in correcting the format for snoozing event or deadline tasks.
 */
public class InvalidSnoozeFormatException extends WenJieException {

    /**
     * Constructs an InvalidSnoozeFormatException with a default error message.
     * The message provides guidance on the correct format for the snooze command.
     */
    public InvalidSnoozeFormatException() {
        super("test");
    }

    /**
     * Returns a custom error message that instructs the user on how to properly format
     * the snooze command for either an event or a deadline task.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "Master~~ you didn’t input the format for your snooze command properly T.T \n"
                + "Please provide your snooze command in this format: \n"
                + "For snoozing an event: snooze <task number in list> "
                + "/from <dd/mm/yyyy hhhh> /to <dd/mm/yyyy hhmm> \n"
                + "For snoozing a deadline: snooze <task number in list> /by <dd/mm/yyyy hhmm>";
    }
}
