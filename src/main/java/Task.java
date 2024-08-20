public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark task done with X
    }

    public void markAsDone() throws TaskonException {
        if (isDone) {
            throw new TaskonException("You have already completed this task!\n" + this + "\n");
        }
        this.isDone = true;
    }

    public void markAsUndone() throws TaskonException {
        if (!isDone) {
            throw new TaskonException("Hmm... it looks like you've already left this task unchecked.\n" + this + "\n");
        }
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
