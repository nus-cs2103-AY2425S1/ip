package fridayexception;

/**
 * Represents an exception where the delete argument is invalid.
 */
public class InvalidDeleteArgument extends FridayException {

    /**
     * Constructs an InvalidDeleteArgument exception.
     */
    public InvalidDeleteArgument() {
        super("Please key in a valid task number to be deleted." + "\n"
                + "     Please enter a valid delete command: delete <task number>" + "\n"
                + "     To view the list of tasks, type 'list'.");
    }
}
