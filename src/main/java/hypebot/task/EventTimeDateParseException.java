package hypebot.task;

import java.time.format.DateTimeParseException;

public class EventTimeDateParseException extends TaskDateTimeParseException {
    public EventTimeDateParseException(String message, CharSequence parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
    }
}
