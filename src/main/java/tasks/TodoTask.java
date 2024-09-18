package tasks;

/**
 * Represents a todo task that is characterized by a description and an optional note.
 * A TodoTask does not have specific timing associated with it.
 */
public class TodoTask extends Task {
    private static final String SYMBOL = "T";

    /**
     * Constructs a TodoTask with a description. The task is initially marked as not done.
     *
     * @param description The description of the todo task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Constructs a TodoTask with a description and an optional note. The task is initially marked as not done.
     *
     * @param description The description of the todo task.
     * @param note An optional note associated with the todo task.
     */
    public TodoTask(String description, String note) {
        super(description, note);
    }

    /**
     * Gets the symbol representing the type of task.
     *
     * @return A string symbol representing this task type ("T" for TodoTask).
     */
    public String getSymbol() {
        return SYMBOL;
    }

    /**
     * Gets a string representation of the timings associated with the todo task.
     * As a todo task does not have specific timings, this method returns an empty string.
     *
     * @return An empty string indicating no timings for the todo task.
     */
    public String getTimings() {
        return "";
    }

    /**
     * Returns a string representation of the TodoTask, including its status,
     * description, and any associated note.
     *
     * @return A formatted string representing the TodoTask.
     */
    @Override
    public String toString() {
        String taskString = String.format("[%s][%s] %s", this.SYMBOL, super.getStatusIcon(), super.description);
        taskString += "\nNote: " + super.note + "\n";
        return taskString;
    }
}
