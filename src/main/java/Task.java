package main.java;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // X means task is done
    }

    public void setStatus(boolean newStatus) {
        isDone = newStatus;
    }

    @Override
    public String toString() {
        return description;
    }

}