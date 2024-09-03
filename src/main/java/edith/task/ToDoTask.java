package edith.task;

public class ToDoTask extends Task {
    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String convertToFileFormat() {
        String divider = " | ";
        String status = this.getCompletionStatus() ? "1" : "0";
        return "T" + divider + status + divider + this.getTaskName();
    }

    @Override
    public String toString() {
        if (this.getCompletionStatus()) {
            return "[T][X] " + this.getTaskName();
        } else {
            return "[T][ ] " + this.getTaskName();
        }
    }
}
