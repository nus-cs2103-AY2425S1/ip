package sage.Task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    public Task(String description, String tag) {
        this.description = description;
        this.tag = tag;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the done status of the task.
     *
     * @param isDone True if the task is completed, otherwise false.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (#%s)", getStatusIcon(), description, tag);
    }

    public String toSave() {
        return String.format("%s | %s | %s", isDone ? "1" : "0", tag, description);
    }
}