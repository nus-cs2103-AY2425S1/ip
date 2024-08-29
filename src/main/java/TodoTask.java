public class TodoTask extends Task {

    public TodoTask(String taskItem, boolean isCompleted) {
        super(taskItem, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDatabaseFormat() {
        return "T | " + (this.isCompleted() ? "1" : "0") + " | " + this.getTaskItem();
    }
}
