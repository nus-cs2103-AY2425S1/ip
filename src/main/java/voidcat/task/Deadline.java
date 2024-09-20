package voidcat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which has a due date and time.
 */
public class Deadline extends Task {

    protected static final DateTimeFormatter FORMATTER_DATETIME = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The deadline of the task in the format "uuuu-MM-dd HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, FORMATTER_DATETIME);
    }

    /**
     * Constructs a new Deadline task with the specified description, due date,
     * and completion status to save to a file.
     *
     * @param description The description of the task.
     * @param by The deadline of the task in the format "uuuu-MM-dd HHmm".
     * @param done 1 if the task is done, 0 if not done.
     */
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
        return String.format("%s | %d | %s | %s", this.getTaskType(), (this.isDone ? 1 : 0),
                this.description, this.by.format(FORMATTER_DATETIME));
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d uuuu, h:mma")) + ")";
    }
}
