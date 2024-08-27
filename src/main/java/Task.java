public abstract class Task {
    protected String description;
    protected boolean isDone;

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

    public String formatSave() {
        return (isDone ? "1" : "0")
                + " | "
                + this.description;
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
