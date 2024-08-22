public class Task {
    private boolean isDone = false;
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format(
            "[%s] %s",
            this.isDone ? "X" : " ",
            this.name
        );
    }
}
