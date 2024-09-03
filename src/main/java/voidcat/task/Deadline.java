package voidcat.task;

import voidcat.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDateTime by;
    protected static final DateTimeFormatter FORMATTER_DATETIME = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, FORMATTER_DATETIME);
    }

    public Deadline(String description, String by, int done) {
        super(description);
        this.by = LocalDateTime.parse(by, FORMATTER_DATETIME);
        if (done == 1) {
            this.isDone = true;
        }
    }
    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %d | %s | %s", this.getTaskType(), (this.isDone ? 1 : 0), this.description, this.by.format(FORMATTER_DATETIME));
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d uuuu, h:mma")) + ")";
    }
}