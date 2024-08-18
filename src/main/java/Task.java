public class Task {
    private String taskName;
    private boolean isCompleted;

    /**
     * Constructor for a new incomplete task
     * 
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Mark a task as completed
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Mark a task as incomplete
     */
    public void markAsNotCompleted() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return this.isCompleted ? "[X] " + this.taskName : "[ ] " + this.taskName;
    }
}
