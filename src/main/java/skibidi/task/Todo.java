package skibidi.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return " [T]" + super.toString();
    }

    @Override
    public String writeToFile() {
        return "T | " + super.writeToFile();
    }
}
