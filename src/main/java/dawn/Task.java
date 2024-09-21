package dawn;
public class Task {
    private String desc;
    private boolean isDone;

    public Task() {
        this.desc = "";
        this.isDone = false;
    }

    /**
     * Creates a new instance of a task
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.desc = description.trim();
        this.isDone = false;
    }

    /**
     * Returns the status of the task as an icon of "X" for done and " " for not done
     *
     * @return status of the task as an icon
     */
    protected String getStatusIcon() {
        return isDone ? "X" : "  ";
    }

    /**
     * Returns the status of the task as a boolean
     *
     * @return status of the task as a boolean
     */
    protected boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    /**
     * Sets the status of the task as done
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the status of the task as not done
     */
    protected void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the task description
     */
    protected String getDesc() {
        return this.desc;
    }

    protected boolean isAMatch(String d) {
        String capitalDesc = this.desc.toUpperCase();
        return capitalDesc.contains(d.toUpperCase());
    }
}
