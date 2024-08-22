package main.java;

public class NoDateException extends Exception{
    public NoDateException () {
        super("Error: No date provided");
    }
}
