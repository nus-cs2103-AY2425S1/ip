package Arona;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for the deadline class which encapsulates a task with a set deadline but irrelevant/any start date
     * @param  description  the name of the task
     * @param  by  the deadline given in LocalDate readable format, time not included
     */
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
