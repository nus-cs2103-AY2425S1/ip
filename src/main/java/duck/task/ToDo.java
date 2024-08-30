package duck.task;

/**
 * Represents a duke.task.ToDo task with a description.
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
     * Returns a string representation of this duke.task.ToDo task.
     *
     * @return A string representation of this duke.task.ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}