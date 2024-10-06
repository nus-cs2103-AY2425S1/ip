package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the user fails to follow up with a task
 * after a command. This exception provides a custom error message to guide the user
 * in providing a task along with their command.
 */
public class NoFollowUpException extends WenJieException {

    /**
     * Constructs a NoFollowUpException with a default error message.
     */
    public NoFollowUpException() {
        super("test");
    }

    /**
     * Returns a custom error message that instructs the user to include a task
     * with their command in the correct format.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "eh paiseh ah bro, you need to tell me one task along with your \n "
                + "command in this format 'todo/event/deadline *insert your task here*'";
    }
}
