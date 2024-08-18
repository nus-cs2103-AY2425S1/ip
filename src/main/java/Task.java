public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create a new Todo object
     * @param description The description of the Todo Object
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the todo object as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark the todo object as completed
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
