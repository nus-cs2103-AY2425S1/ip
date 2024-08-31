package lbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dueDate;
    private final String taskType = "[D]";
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
        this.type = taskType;
    }

    public Deadline(String description, boolean isComplete, LocalDateTime dueDate) {
        super(description, isComplete);
        this.dueDate = dueDate;
        this.type = taskType;
    }

    @Override
    public String toString() {
        return this.type + "[" + this.status() + "] " + this.description + " (by: " + dueDate.format(dateTimeFormat) + ")";
    }
}
