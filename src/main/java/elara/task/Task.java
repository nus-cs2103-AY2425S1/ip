public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        description = desc;
        isDone = false;
    }

    public Task(String desc, boolean isDone) {
        description = desc;
        this.isDone = isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract String toFileFormat();

}
