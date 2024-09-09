package sigma.task;

import java.time.LocalDateTime;

/**
 * Represents a to-do task.
 */
public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusString(), getDescription());
    }

    @Override
    public LocalDateTime getStartDate() {
        return null;
    }

    @Override
    public String getDateString() {
        return "";
    }

}
