package chatkaki.tasks;

import chatkaki.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;

    private DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }


    @Override
    public String fileFormat() {

        return "DEADLINE," + super.fileFormat() + "," + this.by.format(FORMATTER);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }
}
