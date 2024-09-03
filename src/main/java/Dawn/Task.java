package Dawn;
public class Task {
    protected String desc;
    protected boolean isDone;

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
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the status of the task as a boolean
     *
     * @return status of the task as a boolean
     */
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    /**
     * Sets the status of the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the status of the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the task description
     */
    public String getDesc() {
        return this.desc;
    }
}
