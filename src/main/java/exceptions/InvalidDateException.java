package main.java.exceptions;

public class InvalidDateException extends Exception{
    public InvalidDateException () {
        super("Error: No date provided");
    }

    public InvalidDateException (String message) {
        super("Error: " + message);
    }
}
