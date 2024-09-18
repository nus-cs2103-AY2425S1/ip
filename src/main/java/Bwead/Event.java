package Bwead;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start date and end date.
 */
public class Event extends Task {

    private boolean isDone;
    private String text;
    private LocalDate start;
    private LocalDate end;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructs an Event task with a task name, start date, end date, start time, and end time.
     *
     */
    public Event(String text, LocalDate start, LocalDate end, LocalTime startTime, LocalTime endTime) {
        super(text);
        this.text = text;
        this.start = start;
        this.end = end;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        assert this.text != null;
        return this.text;
    }

    public void setDatesTimes(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        this.start = startDate;
        this.end = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of an event task.
     *
     * @return String representation of an event.
     */
    public String toString() {
        String str = "";
        if (isDone) {
            str = "X";
        } else {
            str = " ";
        }
        return "[E][" + str + "] " + text + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + startTime.format(DateTimeFormatter.ofPattern("HH.mm")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + endTime.format(DateTimeFormatter.ofPattern("HH.mm")) + ")";
    }
}
