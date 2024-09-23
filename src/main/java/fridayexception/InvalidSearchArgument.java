package fridayexception;

/**
 * Represents an exception where the search argument is invalid.
 */
public class InvalidSearchArgument extends FridayException {

    /**
     * Constructs an InvalidSearchArgument exception.
     */
    public InvalidSearchArgument() {
        super("The search keyword cannot be empty." + "\n"
                + "     Please enter a valid search command: search <date>.");
    }
}
