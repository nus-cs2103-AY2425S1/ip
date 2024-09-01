package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        Task.taskCount++;
    }


    @Override
    public String toDataString() {
        return "D | " + super.toDataString() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(outputFormatter) + ")";
    }

    public LocalDate getDeadlineDate() {
        return this.by;
    }
}
