package Bwead;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private boolean isDone;
    private String text;
    private LocalDate date;
    private LocalTime time;

    public Deadline(String text, LocalDate date, LocalTime time) {
        super(text);
        this.text = text;
        this.date = date;
        this.time = time;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return this.text;
    }

    public String toString() {
        String str = "";
        if (isDone) {
            str = "X";
        } else {
            str = " ";
        }
        return "[D][" + str + "] " + text + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " +
                time.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}
