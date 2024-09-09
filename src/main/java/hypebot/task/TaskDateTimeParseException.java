package hypebot.task;

import java.time.format.DateTimeParseException;

public class TaskDateTimeParseException extends DateTimeParseException {
    public TaskDateTimeParseException(String message, CharSequence parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
    }
}
