public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskDescription() {
        return this.description;
    }

    public void setIsDone(boolean bool) {
        this.isDone = bool;
    }

}
