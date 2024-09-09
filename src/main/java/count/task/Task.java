package count.task;

/**
 * The Task class has a description and a boolean, isCompleted
 */
public class Task {
    private String description;
    private boolean isCompleted;

    /**
     * Constructor for Task, taking in only a description,
     * setting isCompleted to false
     * @param description String description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Constructor for Task, taking in a String description and a boolean for isCompleted
     * @param description String description of the task
     * @param completion Boolean whether the task is completed
     */
    public Task(String description, boolean completion) {
        this.description = description;
        this.isCompleted = completion;
    }

    public void setCompletion(boolean completion) {
        this.isCompleted = completion;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (this.isCompleted ? "X" : " ");
    }

    public boolean getCompletion() { return isCompleted; }

    @Override
    public String toString() {
        return String.format("[%s] ", this.getStatusIcon()) + this.getDescription();
    }
}
