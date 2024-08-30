package Elon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected boolean isDone;
    protected LocalDate by;
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by =by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.getIsDone()? "1" : "0")
                + " | " + super.toFileString() + " | "
                + this.by;
    }
}