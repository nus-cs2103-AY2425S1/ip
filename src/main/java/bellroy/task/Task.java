package bellroy.task;

/**
 * Abstract Task Class
 */
public abstract class Task {
    protected String description;
    public boolean isDone;
    protected String type;
    private String association;

    public Task(String type, String description) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void undo() {
        this.isDone = false;
    }

    public String getAssociation() {
        return this.association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }
}
