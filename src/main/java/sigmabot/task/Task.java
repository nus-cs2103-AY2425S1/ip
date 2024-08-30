public abstract class Task {
    protected String name;
    protected String description;
    protected boolean isDone;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name +
                "\n\tDescription: " + description;
    }
}
