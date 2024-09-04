public class Task {
    public final String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getStatusBinary() {
        return (this.isDone ? "1" : "0");
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
