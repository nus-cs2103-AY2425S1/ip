public abstract class Task {
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

    abstract char getTaskType();

    @Override
    public String toString() {
        return String.format(
            "[%c][%s] %s",
            getTaskType(),
            this.isDone ? "X" : " ",
            this.name
        );
    }
}
