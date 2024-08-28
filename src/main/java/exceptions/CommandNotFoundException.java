package exceptions;

public class CommandNotFoundException extends GladosException {
    /**
     * Constructs CommandNotFoundException by calling the parent class.
     */
    public CommandNotFoundException() {
        super( "Unknown command. Please try again.");
    }
}
