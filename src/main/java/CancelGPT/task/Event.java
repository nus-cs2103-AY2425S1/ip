package CancelGPT.task;

import CancelGPT.datetime.LocalDateTimeHandler;

import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTimeHandler fromDate;
    protected LocalDateTimeHandler toDate;

    public Event(String description, LocalDateTimeHandler fromDate, LocalDateTimeHandler toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Event(boolean isDone, String description, LocalDateTimeHandler fromDate, LocalDateTimeHandler toDate) {
        super(isDone, description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate.getDisplayedLocalDateTime() 
                + " to: " + toDate.getDisplayedLocalDateTime() + ")";
    }

    @Override
    public String getSavedDataString() {
        return "E" + " | " + super.getSavedDataString() + " | " + fromDate.getLocalDateTimeOriginal() + " | "
                + toDate.getLocalDateTimeOriginal();
    }

    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) throws DateTimeParseException {
        return new Event(getStatusBoolean(Integer.parseInt(savedDataArr[1])), savedDataArr[2],
                LocalDateTimeHandler.parseLocalDateTimeStringToHandler(savedDataArr[3]),
                LocalDateTimeHandler.parseLocalDateTimeStringToHandler(savedDataArr[4]));
    }
}
