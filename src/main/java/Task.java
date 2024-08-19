public class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = "[" + this.getStatusIcon() + "]";
        return status + " " + this.description;
    }
}
