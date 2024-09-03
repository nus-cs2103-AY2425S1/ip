package duck.task;

/**
 * Represents a ToDo task with a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a duke.task.ToDo task with description.
     *
     * @param description The description of the duke.task.ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this todo task.
     *
     * @return A string representation of this todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of this todo task to be saved in duck.txt.
     *
     * @return A string representation of this todo task.
     */
    public String toFileString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}
