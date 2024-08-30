package yapper.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
