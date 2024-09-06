package lemon.task;

public class Todo extends Task{

    public Todo(String description, boolean isDone) {
        super(description, "todo", isDone);
    }

    @Override
    public String toFileString() {
        return "T|" + isDone + "|" + description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
