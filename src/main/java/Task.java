public abstract class Task {
    private String body;
    private boolean isDone;

    public Task(String body) {
        this.body = body;
        this.isDone = false;
    }

    public void setDone() throws IllegalStateException {
        if (this.isDone) {
            throw new IllegalStateException("Task has already been set as completed!");
        }
        this.isDone = true;
    }

    public void setUndone() {
        if (!this.isDone) {
            throw new IllegalStateException("Task has already been set as uncompleted!");
        }
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[X] " + this.body
                : "[ ] " + this.body;
    }
}
