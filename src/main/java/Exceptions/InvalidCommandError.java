package Exceptions;

public class InvalidCommandError extends Exception{
    public InvalidCommandError() {
        super("Invalid command.");
    }
}
