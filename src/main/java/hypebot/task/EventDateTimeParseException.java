package hypebot.task;

public class EventDateTimeParseException extends TaskDateTimeParseException {
    public EventDateTimeParseException(String message, CharSequence parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
    }
}
