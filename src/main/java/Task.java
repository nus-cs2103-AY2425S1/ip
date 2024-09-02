public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public String markAsDone() {
        isDone = true;
        return "\tNice! I've marked this task as done:\n\t\t" + this;
    }

    public void markAsDoneSilently() {
        isDone = true;
    }

    public String markAsUndone() {
        isDone = false;
        return "\tOK, I've marked this task as not done yet:\n\t\t" + this;
    }

    public String announceDeletion() {
        return "\tNoted. I've removed this task:\n\t\t" + this;
    }

    public abstract String dbReadableFormat();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}