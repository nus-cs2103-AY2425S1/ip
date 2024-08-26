package ipman;

public class CommandException extends Exception {
    public CommandException(String errorMessage) {
        super("Error: "+ errorMessage);
    }
}