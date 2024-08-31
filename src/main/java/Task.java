public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the completion state of the task.
     * @param nv the value to set isDone to
     */
    public void setDone(boolean nv) {
        isDone = nv;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is marked as complete.
     * @return The completion state of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        char charDone = ' ';
        if (isDone) charDone = 'X';
        return "[T][" + charDone + "] " + description;
    }
}
