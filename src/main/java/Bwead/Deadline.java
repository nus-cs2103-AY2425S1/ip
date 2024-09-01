package Bwead;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private boolean done;
    private String text;
    private LocalDate date;
    private LocalTime time;

    public Deadline(String text, LocalDate date, LocalTime time) {
        super(text);
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public void setDone(boolean toset) {
        this.done = toset;
    }

    public String getName() {
        return this.text;
    }

    public String toString() {
        String str = "";
        if (done) {
            str = "X";
        } else {
            str = " ";
        }
        return "[D][" + str + "] " + text + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ", " + time.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}
