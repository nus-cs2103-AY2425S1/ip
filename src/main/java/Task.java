public class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

}
