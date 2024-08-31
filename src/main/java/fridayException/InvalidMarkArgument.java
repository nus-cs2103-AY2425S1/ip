package fridayException;

/**
 * Represents an exception where the mark argument is invalid.
 */
public class InvalidMarkArgument extends FridayException {
    public InvalidMarkArgument() {
        super("Please key in a valid task number to mark as done." + "\n"
                + "     Please enter a valid mark command: mark <task number>" + "\n"
                + "     To view the list of tasks, type 'list'.");
    }
}
