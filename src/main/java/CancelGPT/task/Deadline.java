package CancelGPT.task;

import CancelGPT.datetime.LocalDateTimeHandler;

import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTimeHandler byDate;

    public Deadline(String description, LocalDateTimeHandler byDate) {
        super(description);
        this.byDate = byDate;
    }

    public Deadline(boolean isDone, String description, LocalDateTimeHandler byDate) {
        super(isDone, description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
                + byDate.getDisplayedLocalDateTime() + ")";
    }

    @Override
    public String getSavedDataString() {
        return "D" + " | " + super.getSavedDataString() + " | " + byDate.getLocalDateTimeOriginal();
    }

    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) throws DateTimeParseException {
        return new Deadline(getStatusBoolean(Integer.parseInt(savedDataArr[1])),
                savedDataArr[2], LocalDateTimeHandler.parseLocalDateTimeStringToHandler(savedDataArr[3]));
    }
}
