package Tasks;

import enums.TaskType;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with [X] and not done with [ ]
    }

    public void getDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }
}
