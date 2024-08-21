public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatsIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsdone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatsIcon() + "] " + description;
    }
}
