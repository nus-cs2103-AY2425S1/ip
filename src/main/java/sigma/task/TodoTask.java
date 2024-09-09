package sigma.task;

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
    public String getDate() {
        return "";
    }

}
