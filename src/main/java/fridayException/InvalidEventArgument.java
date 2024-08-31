package fridayException;

/**
 * Represents an exception where the event argument is invalid.
 */
public class InvalidEventArgument extends FridayException {
    public InvalidEventArgument() {
        super("The description of an event, the start date and the end date cannot be empty." + "\n"
                + "     Please enter a valid event command: event <description> /from <start date> /to <end date>.");
    }
}
