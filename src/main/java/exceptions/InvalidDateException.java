package exceptions;

public class InvalidDateException extends Exception {
    public InvalidDateException() {
        super("Start date must be before end date.");
    }
}

