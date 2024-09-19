package chacha.task;

/**
 * Represents the Task of users.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates Task object with specified components.
     *
     * @param description Description of task
     * @param isDone Status of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the Task that is marked done.
     *
     * @return Task marked undone.
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Returns the Task that is marked undone.
     *
     * @return Task marked undone.
     */
    public Task markUndone() {
        this.isDone = false;
        return this;
    }

    /**
     * Returns a string representation to be printed.
     *
     * @return String representation.
     */
    public String printTask() {
        String status = (this.isDone ? "X" : " ");
        return "[" + status + "] " + this.description;
    }

    /**
     * Returns a string representation to be written in chacha.txt.
     *
     * @return String representation.
     */
    public String writeTask() {
        return "|";
    }

    /**
     * Checks if the input string in contained in the description.
     *
     * @param input The input string to search for.
     * @return True if the input string is found in description, false otherwise.
     */
    public boolean compareText(String input) {
        return this.description.contains(input);
    }
}
