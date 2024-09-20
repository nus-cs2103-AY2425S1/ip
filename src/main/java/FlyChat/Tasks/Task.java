package flychat.tasks;

/**
 * Represents the Tasks of the user.
 */
public class Task {
    private boolean done;
    private String text;

    /**
     * Constructs a new Task object.
     *
     * @param description
     */
    public Task(String description) {
        this.done = false;
        this.text = description;
    }

    /**
     * Changes the done boolean to true to mark a task.
     */
    public void completeTask() {
        this.done = true;
    }

    /**
     * Changes the done boolean to false to unmark a task.
     */
    public void uncompleteTask() {
        this.done = false;
    }

    /**
     * Generates a string containing the info for the task object in the
     * appropriate format for saving into the save file..
     *
     * @return Formatted string to be saved to the save file.
     */
    public String formatStringForSaving() {
        return toString();
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return text;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + this.text;
    }
}
