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

    public String toString() {
        String str = "";
        if (isDone) {
            str = "X";
        } else {
            str = " ";
        }
        return "[E][" + str + "] " + text + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + startTime.format(DateTimeFormatter.ofPattern("HH.mm")) + " to: " +
                end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " +
                endTime.format(DateTimeFormatter.ofPattern("HH.mm")) + ")";
    }
}
