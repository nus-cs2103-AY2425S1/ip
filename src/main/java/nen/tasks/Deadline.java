package nen.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a Deadline Task
 * @author Gan Ren Yick (A0276246X)
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a task with deadline
     * @param description of the task
     * @param by deadline of String format (yyyy-mm-dd hh:mm)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toData() {
        return "D/" + this.isDone + "/" + this.description + "/" + this.by;
    }
}
