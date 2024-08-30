public abstract class Task {

    public enum TaskType{
        TODO, DEADLINE, EVENT
    }
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;


    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
    }

    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public abstract String getTaskType();

}
