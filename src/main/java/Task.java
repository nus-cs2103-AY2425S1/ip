public abstract class Task {
    protected String description;
    protected boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getStatusIcon() {
        return completed ? "X" : " ";
    }

    public void markDone() {
        this.completed = true;
    }

    public void markUndone() {
        this.completed = false;
    }

    public abstract String getTaskInfo();

    public abstract String getTaskType();


}
