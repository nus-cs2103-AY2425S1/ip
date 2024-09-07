public class Task {
    private String status;
    private boolean isDone;
    private TaskType type;

    public Task(String status, TaskType type) {
        this.status = status;
        this.isDone = false;
        this.type = type;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatus() {
        return status;
    }

    public String getIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getIcon() + " " + status;
    }
}

