public class Task {
    private final String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return String.format("[%s] %s", status, this.description);
    }
}
