package chatsy.tasks;

public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + description;
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
