package seedu.task;

/**
 * The {@code ToDo} class represents a task that only has a description with no specific time
 * associated with it.
 * It extends the {@code Task} class, making it a simple task with no deadlines or event times.
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
     * Converts the {@code ToDo} task into a string representation for display purposes.
     * The format includes the task type "[T]" followed by the task's status and description.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the {@code ToDo} task into a string format suitable for saving in a text file.
     * The format includes the task type "T", completion status (1 for done, 0 for not done),
     * and the task's description.
     *
     * @return A formatted string representing the ToDo task to be saved.
     */
    @Override
    public String toSave() {
        return "T" + " | " + (this.isDone ? 1 : 0) + " | " + this.description;
    }

    /**
     * Compares this {@code ToDo} with the specified object for equality.
     * Two {@code ToDo} tasks are considered equal if they have the same description.
     *
     * @param obj The object to compare this {@code ToDo} with.
     * @return {@code true} if the specified object is a {@code ToDo} with the same description;
     *         {@code false} otherwise.
     */
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
