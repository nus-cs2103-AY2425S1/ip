package shenhe.task;

public class Todo extends Task {


    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
