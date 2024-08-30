package exceptions;

public class StartAfterEndException extends Exception {
    public StartAfterEndException() {
        super("Start date and time is after end date and time");
    }
}
