public class Todo extends Task {
    public Todo(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.TODO);
    }

    @Override
    public String getTaskTypeSymbol() {
        return "T";
    }
    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
