public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    public void setDone() {
       this.isDone = true;
    }

    public void setUndo() {
        this.isDone = false;
    }
}
