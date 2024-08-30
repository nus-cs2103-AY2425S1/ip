package yapmeister.task;

public abstract class Task {
    private final String taskName;
    private boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return this.completed;
    }
    public String getTaskName() {
        return taskName;
    }

    abstract public String exportString();

    @Override
    public String toString() {
        String x;
        if (completed) {
            x = "X";
        } else {
            x = " ";
        }
        return String.format("[%s] %s", x, this.taskName);
    }
}
