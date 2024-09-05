package taskalyn;

public abstract class Task {
    private String taskItem;
    private boolean isCompleted;

    public Task(String taskItem, boolean isCompleted) {
        this.taskItem = taskItem;
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setComplete() {
        this.isCompleted = true;
    }

    public void setIncomplete() {
        this.isCompleted = false;
    }

    public String getTaskDescription() {
        return taskItem;
    }

    @Override
    public String toString() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this.taskItem;
    }

    public abstract String toDatabaseFormat();
}
