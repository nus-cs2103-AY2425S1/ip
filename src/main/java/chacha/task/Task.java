package chacha.task;

/**
 * Represents the Task of users.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates Task object with specified components.
     *
     * @param description
     * @param isDone
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
        String output = "";
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

    public boolean compareText(String input) {
        return this.description.contains(input);
    }
}
