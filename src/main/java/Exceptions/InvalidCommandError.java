package main.java.Exceptions;

public class InvalidCommandError extends Exception{
    public InvalidCommandError() {
        super("Invalid command.");
    }
}
