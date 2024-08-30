public class Task {
    private String status;
    private boolean isDone;

    public Task(String status) {
        this.status = status;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getIcon() + " " + status;
    }
}

