public class Task {
    private final String description;
    private boolean done;

    /**
     * Constructor for Task.
     * @param description
     * @throws IllegalArgumentException
     */
    public Task(String description) throws IllegalArgumentException {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("You can't do a non-existent task.");
        }

        this.description = description;
        this.done = false;
    }

    /**
     * Returns the description of the task.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the status of the task.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Returns the status of the task.
     * @return done
     */
    public boolean isDone() {
        return done;
    }
}
