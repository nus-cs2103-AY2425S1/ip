public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
    }

    private void setIsDone() {
        this.isDone = true;
    }

    private void setIsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String marked = this.isDone ? "X" : "";
        return String.format("[%s] %s", marked, this.name);
    }
}
