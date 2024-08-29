package topaz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm", Locale.ENGLISH)) + ")";
    }

    @Override
    public String toFileRecord() {
        Integer status = isDone ? 1 : 0;
        return "D" + " | " + status + " | " + this.description
                + " | " + this.by;
    }
    @Override
    public String getStatus() {
        return "[D]" + "[" + super.getStatusIcon() + "]" + " " + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm", Locale.ENGLISH)) + ")";
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof Deadline obj) {
            return obj.description.equals(this.description)
                    && obj.by.equals(this.by)
                    && obj.isDone == isDone;
        }
        return false;
    }
}
