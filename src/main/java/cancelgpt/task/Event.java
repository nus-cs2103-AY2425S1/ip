package cancelgpt.task;

import java.time.format.DateTimeParseException;

import cancelgpt.datetime.LocalDateTimeHandler;

/**
 * Represents an event task, with a from date and a to date.
 */
public class Event extends Task {
    protected LocalDateTimeHandler fromDate;
    protected LocalDateTimeHandler toDate;

    /**
     * Initialises Event with a description, a from date, and a to date,
     * with initial status of not done.
     *
     * @param description the description of the event
     * @param fromDate    the from date of deadline
     * @param toDate      the to date of deadline
     */
    public Event(String description, LocalDateTimeHandler fromDate, LocalDateTimeHandler toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Initialises Event with initial status set to isDone, a description, a from date, and a to date,
     *
     * @param isDone      the status of the event
     * @param description the description of the event
     * @param fromDate    the from date of deadline
     * @param toDate      the to date of deadline
     */
    public Event(boolean isDone, String description, LocalDateTimeHandler fromDate, LocalDateTimeHandler toDate) {
        super(isDone, description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns an event task generated from information from savedDataArr array.
     *
     * @param savedDataArr the array containing information to generate the deadline task
     * @return the event task created
     * @throws DateTimeParseException if the local date time in the savedDataArr cannot be parsed
     */
    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) throws DateTimeParseException {
        return new Event(getStatusBoolean(Integer.parseInt(savedDataArr[1])), savedDataArr[2],
                LocalDateTimeHandler.parseLocalDateTimeStringToHandler(savedDataArr[3]),
                LocalDateTimeHandler.parseLocalDateTimeStringToHandler(savedDataArr[4]));
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
}
