public class Task {
    private String description;
    private boolean isDone;

    public Task(String desc, boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }

    public Task(String desc) {
        this(desc, false);
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s",
                this.isDone ? "X" : " ",
                this.description
        );
    }
}
