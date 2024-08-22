package main.java;

public class InvalidDateException extends Exception{
    public InvalidDateException () {
        super("Error: No date provided");
    }
}
