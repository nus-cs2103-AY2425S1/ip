package killjoy.task;

public class Task {
    protected String description;
    protected boolean isDone;
    TaskType taskType = null;

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public void changeStatus() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getTaskStatus() {
        return this.isDone;
    }

    public String getTaskInfo() {
        return null;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
