public class CommandNotFoundException extends Exception {
    public CommandNotFoundException(String input) {
        super(String.format("%s is not a valid command", input));
    }
}
