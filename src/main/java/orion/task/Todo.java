package orion.task;

public class Todo extends Task{
    public Todo(int taskID, String description) {
        super(taskID, description);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}
