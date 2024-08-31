package cloudy;

/**
 * Represents a Todo task in the Cloudy program.
 * A Todo is a task that has a description.
 */
public class Todo extends Task {

    /**
     * Initializes a Todo task with the specified description.
     * @param description The description of the todo task.
     * @param isMarked The completion status of the todo task.
     */
    public Todo(String description, boolean isMarked) {
        super(description, isMarked);
    }

    @Override
    public String printTaskOnList() {
        if (isMarked) {
            return "[T][X] " + this.description;
        } else {
            return "[T][ ] " + this.description;
        }

    }

    @Override
    public String toFileFormat() {
        return "T | " + (this.isMarked ? "1" : "0") + " | " + this.description;
    }
}
