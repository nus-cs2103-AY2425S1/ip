public class Todo extends Task {
    public Todo(String description) {
        super(description);
        type = TaskType.TODO;
    }

    protected String getTaskType() {
        return "T";
    }

    @Override
    public String toTaskString() {
        return String.format("%s|%d|%s",
                getTaskType(), isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getTaskType(), super.toString());
    }
}
