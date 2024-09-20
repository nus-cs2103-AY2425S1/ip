package chacha.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import chacha.parser.TimeParser;

/**
 * Represents the Task that is an Event for the user.
 */
public class EventTask extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Creates a EventTask object with description, isDone status, date, startTime and endTime.
     *
     * @param description Description of task
     * @param isDone Status of task
     * @param date Date of task
     * @param startTime Start time of task
     * @param endTime End time of task
     */
    public EventTask(String description, boolean isDone, LocalDate date, LocalTime startTime, LocalTime endTime) {
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
                + " from: " + TimeParser.parseTimeToString(this.startTime)
                + " to: " + TimeParser.parseTimeToString(this.endTime) + ")";
    }

    /**
     * Returns a string representation to be written in chacha.txt.
     *
     * @return String representation.
     */
    @Override
    public String writeTask() {
        String output = "E | ";
        String status = (super.isDone ? "1" : "0");
        output += status + " | ";
        return output + description + " | " + date.toString()
                + " | " + TimeParser.parseTimeToString(startTime)
                + "-" + TimeParser.parseTimeToString(endTime) + "\n";
    }
}
