package exceptions;

public class InvalidEventFormatException extends HimException {
    public InvalidEventFormatException() {
        super("Events need a description, a start time and an end time!\n" +
                "Use the format: \"event [description] /start [start date] /at [start time] " +
                "/end [end date] /at [end time]\"");
    }
}
