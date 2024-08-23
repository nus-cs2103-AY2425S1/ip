public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task() {
        new Task("");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String getTaskWithStatus() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    /**
     * Sets task as done.
     *
     * @return true if task completion status changed.
     */
    public boolean markTask() {
        if (!isDone) {
            isDone = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets task as undone.
     *
     * @return true if task completion status changed.
     */
    public boolean unmarkTask() {
        if (isDone) {
            isDone = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
