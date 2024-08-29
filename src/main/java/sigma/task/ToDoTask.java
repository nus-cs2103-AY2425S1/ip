package sigma.task;

/**
 * Represents a to-do task.
 */
public class ToDoTask extends Task {

    public ToDoTask(String desc) {
        super(desc);
    }

    public String getTaskType() {
        return "T";
    }
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusString(), getDesc());
    }

}
