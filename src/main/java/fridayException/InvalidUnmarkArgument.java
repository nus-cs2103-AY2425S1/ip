package fridayException;

/**
 * Represents an exception where the unmark argument is invalid.
 */
public class InvalidUnmarkArgument extends FridayException {
    public InvalidUnmarkArgument() {
        super("Please key in a valid task number to unmark as done." + "\n"
                + "     Please enter a valid unmark command: unmark <task number>" + "\n"
                + "     To view the list of tasks, type 'list'.");
    }
}
