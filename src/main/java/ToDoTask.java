public class ToDoTask extends Task {
    public ToDoTask(String taskName) {
        super(taskName);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toFileFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getTaskName();
    }
}
