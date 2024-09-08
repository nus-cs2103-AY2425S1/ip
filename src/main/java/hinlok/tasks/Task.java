package hinlok.tasks;

public class Task {
    protected boolean isDone;
    protected String name;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;

    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getStatus() + "] " + getName();
    }

    public String getName() {
        return this.name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }


}
