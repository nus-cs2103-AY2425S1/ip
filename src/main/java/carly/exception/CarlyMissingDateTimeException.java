package carly.exception;

/**
 * Exception thrown when a required date or time component is missing in a task description.
 */
public class CarlyMissingDateTimeException extends CarlyException {

    /**
     * Constructs a new CarlyMissingDateTimeException with a detailed error message.
     * The message specifies the missing component in the task description and prompts the user to reenter the input.
     *
     * @param missingCommand A description of the missing component, such as "task description" or "date/time command".
     */
    public CarlyMissingDateTimeException(String missingCommand) {
        super("No " + missingCommand  + " detected :(. Please reenter.");
    }

}
