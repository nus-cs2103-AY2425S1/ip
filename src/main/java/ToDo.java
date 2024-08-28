public class ToDo extends Task {

    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    public ToDo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description;
    }
}