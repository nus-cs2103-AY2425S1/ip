package task;

/**
 * Represents the tasks that is being input into the chatbot
 */
public class Task {
    protected String description;
    protected boolean isDone;

    private Priority priority;

    public enum Priority {
        HIGH,
        MEDIUM,
        LOW;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        priority = Priority.LOW;
    }

    public Priority getPriority() {
        return this.priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Gets a letter representing the priority of the task.
     *
     * @return one word string to represent priority of task
     */
    public String getShortPriority() {
        if (priority == Priority.HIGH) {
            return "H";
        } else if (priority == Priority.MEDIUM) {
            return "M";
        }
        return "L";
    }

    /**
     * Gets a string representation of whether the task is completed.
     *
     * @return String "X" if complete and " " if incomplete.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "][" + this.getShortPriority() + "] " + this.getDescription();
    }
}
