package edith.task;

/**
 * This class extends the Task class. The DeadlineTask inherits the following fields: String taskName,
 * boolean isCompleted.
 */
public class ToDoTask extends Task {
    /**
     * Constructor for ToDoTask class.
     * @param taskName Name of task.
     */
    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toFileFormat() {
        String divider = " | ";
        String status = this.getStatus() ? "1" : "0";
        return "T" + divider + status + divider + this.getTaskName();
    }

    @Override
    public String toString() {
        if (this.getStatus()) {
            return "[T][X] " + this.getTaskName();
        } else {
            return "[T][ ] " + this.getTaskName();
        }
    }
}
