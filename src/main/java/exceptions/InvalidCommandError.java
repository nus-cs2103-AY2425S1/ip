package exceptions;

public class InvalidCommandError extends Exception{
    public InvalidCommandError() {
        super("Invalid command.");
    }
}
