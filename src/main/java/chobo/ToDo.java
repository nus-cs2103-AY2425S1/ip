package chobo;

/**
 * Represents a task that does not have a specific date/time associated with it.
 * A chobo.ToDo object corresponds to a general task in Chobo chatbot.
 */
public class ToDo extends Task {
    /**
     * Creates a new chobo.ToDo task.
     *
     * @param name The description of the task.
     * @param done The status of the task (true if done, false otherwise).
     */
    public ToDo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String getType() {
        return "T";
    }

    public String toFileString() {
        return String.format("%s|%d|%s", this.getType(), this.getIsDone() ? 1 : 0, this.getName());
    }

    /**
     * Returns a string representation of the chobo.ToDo task, including its type,
     * status, and name.
     *
     * @return A string representing the chobo.ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
