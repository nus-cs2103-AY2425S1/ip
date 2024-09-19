package lama.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent an event task with a string description and a boolean status indicating whether the task is done.
 * Subclass of Task, that is represented by [D].
 */
public class Deadline extends Task {

    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Date for the deadline */
    protected LocalDate by;

    /**
     * Construct a deadline task with specified description and date of deadline
     *
     * @param description Description of the deadline task
     * @param by Date of the deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Return a string representation of a deadline task.
     * The format of the string is "[D][status] description (by: date)".
     * The date will follow the format of MMM dd yyyy
     * M represents the month.
     * d represents the date.
     * y represents the year.
     * Status will show "X" if done, else " ".
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Convert the event task to a string format that is suitable to save in file.
     * The format of string is "T | status | description | date".
     * The date follow the format of yyyy-MM-dd.
     * M represents the month.
     * d represents the date.
     * y represents the year.
     * Status will show "1" if done, else "0".
     *
     * @return String representation of deadline task in file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + by.format(FILE_FORMAT);
    }

}
