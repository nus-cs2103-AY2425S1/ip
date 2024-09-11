package momo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a Deadline task object that has been created,
 * storing the due date as a {@code LocalDate} object and providing functionality
 * for converting the object to a file string to store in the storage file
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String task, LocalDate by, boolean isCompleted) {
        super(task, isCompleted);
        this.by = by;
    }

    public String toFileString() {
        return "D|" + super.toFileString() + "|" + by;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
