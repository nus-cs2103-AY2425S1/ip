public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "[" + (isDone? "X":" ") + "] " + this.description;
    }
}
