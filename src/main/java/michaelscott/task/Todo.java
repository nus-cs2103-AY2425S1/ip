package michaelscott.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getDescription() {
        return desc;
    }

    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + desc;
    }

    @Override
    public String toFile() {
        return "T | " + (isDone ? "1" : "0") + " | " + desc;
    }
}
