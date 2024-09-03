package main.java.impl.task;

import main.java.interfaces.AbstractTask;

public class Task implements AbstractTask {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String serialize() {
        return this.getStatusIcon() + "|" + this.description;
    }

}
