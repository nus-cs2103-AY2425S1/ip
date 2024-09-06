package sigma.task;

/**
 * Represents a to-do task.
 */
public class TodoTask extends Task {

    public TodoTask(String desc) {
        super(desc);
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
