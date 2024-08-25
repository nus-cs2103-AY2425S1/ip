public class Task {
    private final String description;
    private boolean done;

    /**
     * Constructor for Task.
     * @param description a String describing the Task
     * @throws IllegalArgumentException if description.isEmpty()
     *
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
     * @return a String describing the Task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the status of the task.
     * @param done a boolean that determines whether a task is done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Returns the status of the task.
     * @return a boolean that states whether a task is done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Returns a formatted String that represents the done and description fields of the Task
     * @return a String representation of the Task object
     */
    @Override
    public String toString() {
        char mark = done ? 'X' : ' ';
        return String.format("[%c] %s", mark, description);
    }
}
