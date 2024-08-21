package main.java;

import java.util.HashMap;

public class Task {
    private String description;
    private String taskType;
    private boolean isDone;

    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.taskType, this.getStatusIcon(), this.description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
}
