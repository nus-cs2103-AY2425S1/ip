public class CommandNotFoundException extends GladosException {
    public CommandNotFoundException() {
        super("GLaDOS: Unknown command. Please try again.");
    }
}
