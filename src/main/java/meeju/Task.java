package meeju;

/**
 * Represents a generic task with a description and a status indicating whether the task is done.
 */
public abstract class Task {
    private String taskDescription;
    private Boolean isDone;



    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param taskDescription The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String taskDescription, Boolean isDone) {
        assert isDone != null : "Task completion status is null";
        assert taskDescription != null : "Task description is null";

        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public abstract String getTaskIdentifier();

    public abstract String serializeDetails();

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        if (this.isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getTaskDescription();
    }
}
