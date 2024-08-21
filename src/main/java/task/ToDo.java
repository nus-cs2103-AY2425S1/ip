package task;
public class ToDo extends Task {
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
