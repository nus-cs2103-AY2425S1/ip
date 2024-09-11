package Majima.task;

import Majima.MajimaException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, String by) throws MajimaException {
        super(description, TaskType.DEADLINE);
        try {
            this.deadline = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new MajimaException("Kiryu-chan! Follow the standard format of 'dd-mm-yyyy HHmm'!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + ")";
    }

    @Override
    public String toFileString() {
        return "[D] | " + getStatusIcon() + " | " + getDescription() + " | " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
    }
}
