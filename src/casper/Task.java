package casper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class Task {
    private String description;
    private String taskType;
    private boolean isDone;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    public Task(String description, String taskType, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.taskType, this.getStatusIcon(), this.description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getDescription() {
        return this.description;
    }

    protected String formatDate(LocalDate dateTime) {
        return dateTime.format(FORMATTER);
    }
}
