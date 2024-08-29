package tasks;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }
    /**
     * Gets the description of the task.
     * @return a string containing all the relevant information of the Task.
     */
    public String getDescription() {
        return this.getStatusIcon() + " | " + this.description;
    }
    /**
     * A symbol denoting the task type.
     * @return a string denoting the task type.
     */
    public String getTaskType() {
        return "";
    }
}
