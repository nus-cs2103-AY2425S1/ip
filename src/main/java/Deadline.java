package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String getCategory() {
        return "[D]";
    }

    public String toFriendlyString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
