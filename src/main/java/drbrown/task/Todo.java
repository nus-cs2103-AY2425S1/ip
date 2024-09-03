package drbrown.task;

public class Todo extends Task {

    public Todo(boolean status, String description) {
        super(status, description);
    }

    @Override
    public String toFileString() {
        return "T | " + getStatus() + " | " + getDescription();
    }

    @Override
    public String toUIString() {
        return "Doc, you don't just walk into a store and buy plutonium! But you sure can add this task to your list!\n";
    }

    @Override
    public String toString() {
        return "[T][" + (getStatus() ? "X" : " ") + "] " + getDescription();
    }
}
