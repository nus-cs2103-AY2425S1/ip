package Task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
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
