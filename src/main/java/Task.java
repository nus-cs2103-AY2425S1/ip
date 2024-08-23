public abstract class Task {
    private String taskItem;
    private boolean isCompleted;

    public Task(String taskItem) {
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

    public String getTaskItem() {
        return taskItem;
    }

    @Override
    public String toString() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this.taskItem;
    }
}
