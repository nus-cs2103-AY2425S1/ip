package killua.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isMatched(String keyword) {
        return description.contains(keyword);
    }

    public String toSave() {
        return " | " + (isDone ? 1 : 0) + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
