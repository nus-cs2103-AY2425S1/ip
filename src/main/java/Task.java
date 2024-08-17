public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon
     *
     * @return "X" if marked done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true; // mark task done
    }

    public void unmark() {
        isDone = false; // unmark task done
    }

    /**
     * Returns a formatted message with status icon
     * and description
     *
     * @return display message
     */
    public String getDisplayMessage() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
