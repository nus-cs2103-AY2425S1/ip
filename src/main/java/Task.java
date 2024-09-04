public class Task {
    public final String description;
    public boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone() {
        if (this.isDone) {
            return;
        }
        this.isDone = true;
    }

    public void markAsNotDone() {
        if (!this.isDone) {
            return;
        }
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
