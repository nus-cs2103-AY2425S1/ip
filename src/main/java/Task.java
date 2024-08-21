public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean markAsDone() {
        if (this.isDone) return false;
        this.isDone = true;
        return true;
    }

    public boolean markAsNotDone() {
        if (!this.isDone) return false;
        this.isDone = false;
        return true;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + name;
    }
}
