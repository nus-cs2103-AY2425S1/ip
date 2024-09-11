package tudee.task;

/**
 * Represents a To-Do task.
 * A To-Do task is a task without any time.
 */
public class ToDo extends Task {
    private static final int MARKED_VALUE = 1;
    private static final int UNMARKED_VALUE = 0;

    /**
     * Constructs a new To-Do task
     *
     * @param taskString The task description.
     */
    public ToDo(String taskString) {
        super(taskString);
    }

    /**
     * Converts the To-Do task into a formatted string for storage in a file.
     *
     * @return A formatted string for the To-Do task.
     */
    @Override
    public String toFileString() {
        return "T | " + (done ? MARKED_VALUE : UNMARKED_VALUE) + " | " + taskString;
    }

    /**
     * Returns a string representation of the To-Do task consisting
     * its type and completion status.
     *
     * @return The string representation of the To-Do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
