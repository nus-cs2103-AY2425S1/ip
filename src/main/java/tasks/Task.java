package tasks;

import enums.TaskType;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String getTaskTypeIcon();
    public abstract TaskType getTaskType();
    public abstract String getDescription();
    public abstract String getSaveFormat();

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); 
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getTaskTypeIcon() + this.getStatusIcon() + " " + this.getDescription();
    }
}
