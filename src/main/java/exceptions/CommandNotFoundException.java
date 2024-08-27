package exceptions;

public class CommandNotFoundException extends GladosException {
    public CommandNotFoundException() {
        super( "Unknown command. Please try again.");
    }
}
