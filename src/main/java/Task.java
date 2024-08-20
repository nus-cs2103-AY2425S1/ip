public class Task {
    private String name;
    private Boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    // Does not consider previous state handling
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getTaskStatus(), this.name);
    }
}
