

abstract public class Task {
    protected String description;

    protected String taskType;
    protected boolean isDone;

    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskType() {
        return this.taskType;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    abstract public String toFileSaveString();
}
