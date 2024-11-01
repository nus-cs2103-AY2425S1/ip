package darkpool.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + this.description;
    }

    @Override
    public String toFileString() {
        return ("T | " + (isDone ? "1" : "0") + " | " + this.description + "\n");
    }

}
