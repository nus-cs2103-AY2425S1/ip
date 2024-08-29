package Bwead;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private boolean done;
    private String text;
    private LocalDate start;
    private LocalDate end;

    public Event(String text, LocalDate start, LocalDate end) {
        super(text);
        this.text = text;
        this.start = start;
        this.end = end;
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
        return "[E][" + str + "] " + text + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
