abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void checkTask() throws ReginaException {
        if (isDone) {
            throw new ReginaException("Task is already marked!");
        }
        this.isDone = true;
    }

    public void uncheckTask() throws ReginaException {
        if (!isDone) {
            throw new ReginaException("Task is already unmarked!");
        }
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
