abstract class Task {
    private final String description;
    private boolean isDone;

    Task(char type, String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getDescription() {
        return description;
    }

    Task markAsDone() {
        this.isDone = true;
        return this;
    }

    Task markAsNotDone() {
        this.isDone = false;
        return this;
    }

    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public String toString() {
        String s = "[" + this.getStatusIcon() + "] ";
        s += this.getDescription();
        return s;
    }
}
