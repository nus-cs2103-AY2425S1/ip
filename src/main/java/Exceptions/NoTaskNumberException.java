package Exceptions;

public class NoTaskNumberException extends Exception {
    public NoTaskNumberException() {
        super("Hmm... I don't see any task number... Please enter the number of the task!");
    }
}