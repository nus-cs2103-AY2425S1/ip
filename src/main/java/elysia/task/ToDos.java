package elysia.task;

/**
 * Represents a ToDo task with a description.
 */
public class ToDos extends Task {

    /**
     * Constructs a ToDo task with the description.
     *
     * @param description The description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task, including its description.
     *
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task suitable for saving to a text file.
     *
     * @return
     */
    @Override
    public String saveToTxt() {
        int i = this.isDone ? 1 : 0;
        return "T | " + i + " | " + this.description;
    }
}
