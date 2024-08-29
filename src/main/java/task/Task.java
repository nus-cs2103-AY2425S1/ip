package task;

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

    /**
     * Just returns the toString() version of the task.
     * Overriden in subclasses Event and Deadline to circumvent the
     * cleanup of the datetime object by Event::dateTimeToCleanString
     * and Deadline::dateTimeToCleanString (so these functions are not used)
     * 
     * @return a simple string representation of the task
     */
    public String toEasyString() {
        return this.toString();
    }

    /**
     * checks if the particular term is present in the task name
     * 
     * @param term the term to check
     * @return true if the term is present, false otherwise
     */
    public boolean contains(String term) {
        return this.taskName.toLowerCase().contains(term.toLowerCase());
    }
}
