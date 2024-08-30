package barcus.exception;

public class BarcusException extends Exception {
    public BarcusException() {}

    public BarcusException(String message) {
        super(message);
    }
}