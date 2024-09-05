package CancelGPT.task;

import CancelGPT.datetime.LocalDateTimeHandler;

import java.time.format.DateTimeParseException;

/**
 * Represents an event task, with a from date and a to date.
 */
public class Event extends Task {
    protected LocalDateTimeHandler from;
    protected LocalDateTimeHandler to;

    /**
     * Initialises Event with a description, a from date, and a to date,
     * with initial status of not done.
     * 
     * @param description the description of the event
     * @param from the from date of deadline
     * @param to the to date of deadline
     */
    public Event(String description, LocalDateTimeHandler from, LocalDateTimeHandler to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Initialises Event with initial status set to isDone, a description, a from date, and a to date,
     *
     * @param isDone the status of the event
     * @param description the description of the event
     * @param from the from date of deadline
     * @param to the to date of deadline
     */
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

    /**
     * Returns an event task generated from information from savedDataArr array.
     *
     * @param savedDataArr the array containing information to generate the deadline task
     * @return the event task created
     * @throws DateTimeParseException if the local date time in the savedDataArr cannot be parsed
     */
    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) throws DateTimeParseException {
        return new Event(getStatusBoolean(Integer.parseInt(savedDataArr[1])), savedDataArr[2],
                LocalDateTimeHandler.parseLocalDateTime(savedDataArr[3]),
                LocalDateTimeHandler.parseLocalDateTime(savedDataArr[4]));
    }
}
