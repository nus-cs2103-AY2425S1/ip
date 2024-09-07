package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the user provides invalid input.
 * This exception provides a custom error message to guide the user in correcting
 * the format for event or deadline tasks.
 */
public class InvalidDateInputException extends WenJieException {

    /**
     * Constructs an InvalidDateInputException with a default error message.
     */
    public InvalidDateInputException() {
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
        return "eh paiseh ah bro, you need to tell me your start time and end time \n "
                + "in this format 'event *your task* /from *time* /to *time*' OR "
                + "'deadline *your task* /by *day*'";
    }
}
