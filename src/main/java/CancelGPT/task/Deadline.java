package CancelGPT.task;

import CancelGPT.datetime.LocalDateTimeHandler;

import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTimeHandler by;

    public Deadline(String description, LocalDateTimeHandler by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, LocalDateTimeHandler by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getDisplayedLocalDateTime() + ")";
    }

    @Override
    public String getSavedDataString() {
        return "D" + " | " + super.getSavedDataString() + " | " + by.getLocalDateTimeOriginal();
    }

    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) throws DateTimeParseException {
        return new Deadline(getStatusBoolean(Integer.parseInt(savedDataArr[1])),
                savedDataArr[2], LocalDateTimeHandler.parseLocalDateTime(savedDataArr[3]));
    }
}
