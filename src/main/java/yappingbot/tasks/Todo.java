package yappingbot.tasks;

public class Todo extends Task {
    public Todo(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.TODO);
    }

    public Todo() {
        super();
    }

    @Override
    public String getTaskTypeSymbol() {
        return "T";
    }
    @Override
    public String toString() {
        return String.format("%s", super.getTaskName());
    }
}
