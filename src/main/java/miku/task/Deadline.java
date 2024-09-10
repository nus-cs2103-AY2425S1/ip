package miku.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import miku.utility.Priority;

/**
 * Represents a deadline with a date.
 */
public class Deadline extends Task {
    private String date = "";
    private LocalDate localDate;

    /**
     * Initialises a DeadLine object.
     *
     * @param desc the description of the deadline.
     * @param date the date of the deadline.
     */
    public Deadline(String desc, String date) {
        super(desc);
        this.date = date;
        localDate = LocalDate.parse(date);
    }

    /**
     * Overloads the constructor with an extra isDone field.
     *
     * @param desc   description of the deadline
     * @param date   date of the deadline
     * @param isDone is the task finished
     */
    public Deadline(String desc, String date, boolean isDone, Priority priority) {
        super(desc, isDone, priority);
        this.date = date;
        localDate = LocalDate.parse(date);
    }

    /**
     * Returns the string value to be stored in the text file.
     *
     * @return string value to be stored in the text file.
     */
    public String storeValue() {
        return this.stringValue().substring(1, 2) + " | "
                + this.isTaskDone()
                    + " | " + this.getDesc() + " | " + date + " | " + this.getPriority().toString() + "\n";
    }

    @Override
    public String stringValue() {
        return "[D]" + super.stringValue() + " (by: "
                + localDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + " )";
    }

    public int getStartYear() {
        return localDate.getYear();
    }

    public int getStartDayOfYear() {
        return localDate.getDayOfYear();
    }
}
