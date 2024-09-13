package optimus;

// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
/**
 * Represents a Todo task that only has a description without any date or time.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return A string in the format: [T][ ] description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the Todo task data for saving to a file.
     *
     * @return A string in the format: T | 0/1 | description
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
