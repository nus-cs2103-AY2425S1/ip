public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return "[T]";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
