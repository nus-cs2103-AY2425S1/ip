package CancelGPT.task;

import CancelGPT.datetime.LocalDateTimeHandler;

import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTimeHandler from;
    protected LocalDateTimeHandler to;

    public Event(String description, LocalDateTimeHandler from, LocalDateTimeHandler to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isDone, String description, LocalDateTimeHandler from, LocalDateTimeHandler to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getDisplayedLocalDateTime() + " to: " + to.getDisplayedLocalDateTime() + ")";
    }

    @Override
    public String getSavedDataString() {
        return "E" + " | " + super.getSavedDataString() + " | " + from.getLocalDateTimeOriginal() + " | "
                + to.getLocalDateTimeOriginal();
    }

    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) throws DateTimeParseException {
        return new Event(getStatusBoolean(Integer.parseInt(savedDataArr[1])), savedDataArr[2],
                LocalDateTimeHandler.parseLocalDateTime(savedDataArr[3]),
                LocalDateTimeHandler.parseLocalDateTime(savedDataArr[4]));
    }
}
