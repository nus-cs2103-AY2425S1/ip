package tasks;

public class ToDoTask extends Task {

    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String getDescription() {
        return this.getTaskType() + " | " + super.getDescription();
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s",
                this.getTaskType(), getStatusIcon(), super.description);
    }
}
