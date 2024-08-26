public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskType() {
        return "Todo";
    }

    @Override
    public String getTimeConstraint() {
        return "No time constraint";
    }
}
