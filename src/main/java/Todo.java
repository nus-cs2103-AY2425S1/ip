public class Todo extends Task {
    protected String description;
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
