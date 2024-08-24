package Task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    protected String getTaskType() {
        return "ToDo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
