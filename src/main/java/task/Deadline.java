package task;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}