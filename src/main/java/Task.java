// TODO: Change this into an interface for later levels
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}