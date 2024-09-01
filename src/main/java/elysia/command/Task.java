package elysia.command;

import java.lang.annotation.Native;
/**
 * Represents a basic task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a basic task with the description
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Keeps track of task's status
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    public abstract String saveToTxt();

    //...
}
