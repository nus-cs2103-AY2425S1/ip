package julie.task;

/**
 * An abstract class that encapsulates the Tasks stored by the Chat Bot.
 */
public abstract class Task {
    /** The string description of the Task. */
    public final String taskString;
    /** The completion status of the task */
    private boolean isCompleted;

    /**
     * Public constructor for a Task.
     *
     * @param s The description for the task.
     */
    public Task(String s) {
        this.taskString = s;
        isCompleted = false;
    }

    /**
     * Marks the task as completed if it was initially not completed.
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete if it was initially complete.
     */
    public void unmarkCompleted() {
        this.isCompleted = false;
    }
    @Override
    public String toString() {
        String output = "";
        if (isCompleted) {
            output = "[x]";
        } else {
            output = "[ ]";
        }
        return String.format("%s %s", output, taskString);
    }

    /**
     * Returns the representation of the task for storage purposes.
     *
     * @return The string representation of the task in storage format.
     */
    public abstract String toStorageString();
}
