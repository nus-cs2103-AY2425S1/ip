package MeowMeow;

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

    public void markDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public String toFileFormat() {
        return (this instanceof ToDo ? "T" : this instanceof Deadline ? "D" : "E") + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public String getDescription() {
        return this.description;
    }
}
