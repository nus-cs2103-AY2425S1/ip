public class Task {
    private String description;
    private boolean isDone;
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", this.description);
        }
        return String.format("[] %s", this.description);
    }
}