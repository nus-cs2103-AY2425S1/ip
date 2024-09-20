package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the user provides invalid input for the Snooze Command.
 * This exception provides a custom error message to guide the user in correcting
 * the format for event or deadline tasks.
 */
public class InvalidSnoozeFormatException extends WenJieException {

    /**
     * Constructs an InvalidSnoozeFormatException with a default error message.
     */
    public InvalidSnoozeFormatException() {
        super("test");
    }

    /**
     * Returns a custom error message that provides instructions to the user
     * on the correct input format for event or deadline tasks.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "Master~~ you didnt input the format for your snooze command properly T.T \n "
                + "Please provide your snooze command in this format: \n"
                + "If you are are snoozing an event: snooze <task number in list> "
                + "/from <dd/mm/yyyy hhhh> /to <dd/mm/yyyy hhhh> \n"
                + "If you are are snoozing a deadline: snooze <task number in list> /by <dd/mm/yyyy hhhh>";
    }
}
