package chobo;

/**
 * The type Task.
 */
public abstract class Task {
    private boolean isDone;
    private String name;

    /**
     * Instantiates a new Task.
     *
     * @param name the name
     * @param done the done
     */
    public Task(String name, boolean done) {
        this.isDone = done;
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public abstract String getType();

    /**
     * To file string string.
     *
     * @return the string
     */
    public abstract String toFileString();

    /**
     * Mark.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Get is done boolean.
     *
     * @return the boolean
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Unmark.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String checked = this.isDone ? "[X] " : "[ ] ";
        return (checked + this.name);
    }
}
