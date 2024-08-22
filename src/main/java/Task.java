public class Task {
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    protected String description;
    protected boolean isDone;
    protected final TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public boolean getStatus() {
        return this.isDone;
    }
    public String getDescription() {
        return this.description;
    }

    public String stringStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void changeStatus() {
        this.isDone = !isDone;
    }
    @Override
    public String toString() {
        return "[" + stringStatus() + "] " + this.description;
    }
}
