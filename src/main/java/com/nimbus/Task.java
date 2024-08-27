package com.nimbus;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone(){
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public abstract String toFileFormat();
    public abstract String getTypeIcon();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
