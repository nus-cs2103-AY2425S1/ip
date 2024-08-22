public abstract class Task {
    private final String taskName;
    private boolean status;
    public Task(String taskName) {
        this.taskName = taskName.trim();
        this.status = false;
    }

    public void mark() {
        this.status = true;
    }

    public void unmark() {
        this.status = false;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", this.status ? "X" : " ", this.taskName);
    }
}
