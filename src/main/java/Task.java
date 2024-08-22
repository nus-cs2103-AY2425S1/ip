public class Task {
    protected String description;
    protected boolean isDone;

    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskType = "[ ]";
    }

    public String getTaskType() {
        return taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    public void markDone() {
        isDone = true;
    }


}
