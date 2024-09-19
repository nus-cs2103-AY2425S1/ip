package pochat.exceptions;

/**
 * Throws when the event format is invalid. This could be due to an empty description
 *     or missing parameters
 */
public class EventFormatInvalidException extends Exception {
    /**
     * Informs the user what went wrong (event format is invalid)
     */
    public EventFormatInvalidException() {
        super("Event format is invalid! Please enter all events as:\n"
            + "event <task_description> /from <start_time in dd/mm/yyyy HHMM> /to "
            + "<end_time> in dd/mm/yyyy HHMM>");
    }
}
