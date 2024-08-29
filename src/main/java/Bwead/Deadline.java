package Bwead;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private boolean done;
    private String text;
    private LocalDate date;

    public Deadline(String text, LocalDate date) {
        super(text);
        this.text = text;
        this.date = date;
    }

    public void setDone(boolean toset) {
        this.done = toset;
    }

    public String toString() {
        String str = "";
        if (done) {
            str = "X";
        } else {
            str = " ";
        }
        return "[D][" + str + "] " + text + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
