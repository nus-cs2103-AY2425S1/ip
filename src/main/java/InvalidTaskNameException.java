package main.java;

public class InvalidTaskNameException extends Exception {

    public InvalidTaskNameException (String name) {
        super("Error: The name " + name + " is invalid!");
    }

    public InvalidTaskNameException () {
        super("Error: Please provide a name!");
    }
}
