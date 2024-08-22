package wolfie.task;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone); // call the constructor of the parent class simple task
    }

    @Override
    public String toFileFormat() {
        return "T | " + (getIsDone() ? "1" : "0") + " | " + getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
