package cancelgpt.task;

import java.time.format.DateTimeParseException;

import cancelgpt.datetime.LocalDateTimeHandler;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTimeHandler byDate;

    /**
     * Initialises Deadline with a description and a date to complete the deadline,
     * with an initial status of not done.
     *
     * @param description the description of the deadline
     * @param byDate      the date to complete by
     */
    public Deadline(String description, LocalDateTimeHandler byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Initialises Deadline with a description and a date to complete the deadline,
     * with an initial status set to isDone.
     *
     * @param isDone      the status of the deadline
     * @param description the description of the deadline
     * @param byDate      the date to complete by
     */
    public Deadline(boolean isDone, String description, LocalDateTimeHandler byDate) {
        super(isDone, description);
        this.byDate = byDate;
    }

    /**
     * Returns a deadline task generated from information from savedDataArr array.
     *
     * @param savedDataArr the array containing information to generate the deadline task
     * @return the deadline task created
     * @throws DateTimeParseException if the local date time in the savedDataArr cannot be parsed
     */
    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) throws DateTimeParseException {
        return new Deadline(getStatusBoolean(Integer.parseInt(savedDataArr[1])),
                savedDataArr[2], LocalDateTimeHandler.parseLocalDateTimeStringToHandler(savedDataArr[3]));
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
}
