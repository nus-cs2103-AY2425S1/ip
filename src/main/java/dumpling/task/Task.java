package dumpling.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean hasSubstring(String substring) {
        for (int i = 0; i <= description.length() - substring.length(); i++) {
            if (this.description.substring(i, i + substring.length()).equals(substring)) {
                return true;
            }
        }
        return false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public abstract String getTaskForSaving();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
