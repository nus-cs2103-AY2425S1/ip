package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public String toFileFormat() {
        return (this instanceof ToDo ? "T" :
                this instanceof Deadline ? "D" : "E") +
                " | " + (isDone ? "1" : "0") + " | " + description;
    }
}

