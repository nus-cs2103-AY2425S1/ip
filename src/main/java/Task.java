public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        description = description.trim();
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of a given task
     *
     * @return status icon which is 'X' if done else ' '
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the status of task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * marks the staus of task as not done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Overrides the toString method to give appropriate string representation of
     * a task which is status icon in square brackets followed by the description
     *
     * @return string representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}