public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for Task class, task initialized with user inputted description
     *
     * @param description description of the task command inputted by the user
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * Returns whether the Task has been completed or not
     */
    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
}
