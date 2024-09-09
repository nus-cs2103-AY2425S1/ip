public abstract class Task {
    private String info;
    private TaskStatus isDone;

    public Task(String info) {
        this.info = info;
        this.isDone = TaskStatus.UNDONE;
    }

    public String getInfo() {
        return info;
    }
    public TaskStatus getStatus() {
        return isDone;
    }
    public void setStatus(TaskStatus status) {
        this.isDone = status;
    }

    public abstract String toString();

    public abstract String toFileFormat();
}
