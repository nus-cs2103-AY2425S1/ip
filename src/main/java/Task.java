public class Task {
    private final String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return String.format("[%s] %s", status, this.description);
    }
}
