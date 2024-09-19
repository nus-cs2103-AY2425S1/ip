package pochat.exceptions;

/**
 * Throws when the deadline format entered is invalid. This could be
 *     due to a empty task description for invalid format
 */
public class DeadlineFormatInvalidException extends Exception {
    /**
     * Informs the user what went wrong (deadline format is invalid)
     */
    public DeadlineFormatInvalidException() {
        super("Invalid format for deadline! Please enter in the following format:\n"
            + "deadline <task_name> /by <deadline in dd/mm/yyyy HHMM format>");
    }
}
