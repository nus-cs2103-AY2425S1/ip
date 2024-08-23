public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Task is already marked as done!");
        }
        this.isDone = true;
    }

    public void markNotDone() throws DukeException {
        if (!this.isDone) {
            throw new DukeException("Task is already marked as not done!");
        }
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    public String toSaveString() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}