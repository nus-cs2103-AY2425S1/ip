public class ToDoTask extends Task {
    public ToDoTask(String taskName) {
        super(taskName);
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
