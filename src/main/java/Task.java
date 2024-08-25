public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns a Task object.
     *
     * @param description Description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns Symbolic Representation of whether the Task is done or not.
     *
     * @return symbolic representation of task state
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    @Override
    public String toString() {
        String taskMessage = String.format("[%s] %s", getStatusIcon(), getDescription());
        return taskMessage;
    }
}
