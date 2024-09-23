package fridayexception;

/**
 * Represents an exception where the find argument is invalid.
 */
public class InvalidFindArgument extends FridayException {

    /**
     * Constructs an InvalidFindArgument exception.
     */
    public InvalidFindArgument() {
        super("Please key in a valid keyword to search for tasks." + "\n"
                + "     Please enter a valid find command: find <keyword>" + "\n"
                + "     To view the list of tasks, type 'list'.");
    }
}
