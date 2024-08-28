public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description ;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toFileFormat() {
        String task;
        if (this instanceof Todo) {
            task = "T";
        } else if (this instanceof Deadline) {
            task = "D";
        } else {
            task = "E";
        }
        return task + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description;
    }
}
