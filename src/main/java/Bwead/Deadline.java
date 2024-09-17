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

    /**
     * Constructs a Deadline with its name, date, and time.
     *
     * @param text task name
     * @param date the task's deadline date.
     * @param time the task's deadline time.
     */
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
        assert this.text != null;
        return this.text;
    }

    public void setDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the string representation of a deadline task.
     *
     * @return String representation of a deadline.
     */
    public String toString() {
        String str = "";
        if (isDone) {
            str = "X";
        } else {
            str = " ";
        }
        return "[D][" + str + "] " + text + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + time.format(DateTimeFormatter.ofPattern("HH.mm")) + ")";
    }
}
