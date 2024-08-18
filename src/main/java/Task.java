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

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        String formattedString = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return formattedString;
    }
}
