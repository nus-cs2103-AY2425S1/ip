package seedu.task;

/**
 * The {@code ToDo} class represents a task that only has a description with no specific time associated with it.
 * It extends the {@code Task} class.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task, including its type indicator "[T]".
     *
     * @return A string in the format "[T] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task formatted for saving to a file.
     *
     * @return A string in the format "T | isDone | description".
     */
    @Override
    public String toSave() {
        return "T" + " | " + (this.isDone ? 1 : 0) + " | " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ToDo) {
            ToDo t = (ToDo) obj;
            if (this.description == null || t.description == null) {
                return false;
            }

            return this.description.equals(t.description);
        }

        return false;
    }
}
