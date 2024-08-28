public abstract class Task {
    private String taskName;
    private boolean isCompleted;
    Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void markAsUncompleted() {
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public abstract String writeToFile();

    @Override
    public String toString() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this.taskName;
    }
}
