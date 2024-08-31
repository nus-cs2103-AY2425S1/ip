package momo.task;

import java.util.ArrayList;

public class Task {
    String task;
    boolean isComplete;

    public Task(String task, boolean isComplete) {
        this.task = task;
        this.isComplete = isComplete;
    }

    public String getTask() {
        return this.task;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    public void unmark() {
        this.isComplete = false;
    }

    public String toFileString() {
        return isComplete ? "0|" + task : "1|" + task;
    }

    @Override
    public String toString() {
        return isComplete ? "[X] " + task  + " ": "[ ] " + task + " ";
    }
}
