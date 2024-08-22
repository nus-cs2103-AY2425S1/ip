public class Task {
    private String taskName;
    private boolean isCompleted;
    Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
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

    @Override
    public String toString() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this.taskName;
    }
}
