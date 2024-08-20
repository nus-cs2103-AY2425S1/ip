public class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        char label = this.isDone ? 'X' : ' ';
        return String.format("[%s] %s", label, this.description);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
}
