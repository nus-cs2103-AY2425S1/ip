package chacha.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Task that is an Event for the user.
 *
 */
public class EventTask extends Task {
    protected LocalDate date;
    protected String startTime;
    protected String endTime;

    /**
     * Creates a EventTask object, initialising description, isDone status, date, startTime and endTime.
     *
     * @param description
     * @param isDone
     * @param date
     * @param startTime
     * @param endTime
     */
    public EventTask(String description, boolean isDone, LocalDate date, String startTime, String endTime) {
        super(description, isDone);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation to be printed.
     *
     * @return String representation.
     */
    @Override
    public String printTask() {
        String output = "[E]";
        String status = (super.isDone ? "X" : " ");
        return output + "[" + status + "] " + super.description
                + " (" + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " from: " + this.startTime + " to: " + this.endTime + ")";
    }

    /**
     * Returns a string representation to be written in chacha.txt.
     *
     * @throws String representation.
     */
    @Override
    public String writeTask() {
        String output = "E | ";
        String status = (super.isDone ? "1" : "0");
        output += status + " | ";
        return output + description + " | " + date.toString()
                + " | " + startTime + "-" + endTime + "\n";
    }
}
