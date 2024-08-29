/**
 * Represents a type of Task to be done.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task using the given description.
     *
     * @param description The String description of the Task. 
     */
    public Todo(String description) {
        super(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
