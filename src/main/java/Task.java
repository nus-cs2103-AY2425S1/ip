package main.java;

public class Task {
    protected String description;
    protected boolean isDone; // True means task is done

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getCategory() {
        return "[ ]";
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void setStatus(boolean newStatus) {
        isDone = newStatus;
    }

    @Override
    public String toString() {
        return description;
    }

}