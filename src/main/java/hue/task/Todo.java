package hue.task;
/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Creates a {@code Todo} task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the task to a format suitable for saving to a file.
     *
     * @return A string representing the task in file format.
     */
    @Override
    public String toFileFormat() {
        String task = "T";

        return task + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description;
    }

}
