package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected final TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return this.taskType;
    };

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.taskType.toString() + "][" + this.getStatusIcon() + "] " + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsIncomplete() {
        this.isDone = false;
    }
}
