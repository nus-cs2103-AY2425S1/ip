public class Todo extends Task{
    public Todo(String taskDescription) {
        super(taskDescription, false);
    }

    private final String TASK_ICON = "[T]";

    @Override
    public String toString() {
        return TASK_ICON + super.toString();
    }
}
