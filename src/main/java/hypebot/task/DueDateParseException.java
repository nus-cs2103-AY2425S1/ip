package hypebot.task;

import java.time.format.DateTimeParseException;

public class DueDateParseException extends TaskDateTimeParseException {
    public DueDateParseException(String message, CharSequence parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
    }
}
