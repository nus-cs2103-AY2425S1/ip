package tasks;

import applemazer.*;
import java.io.Serializable;

/**
 * Class that represents a task.
 * These tasks are added to the task list.
 */
public class Task implements Serializable {
    protected final String description;
    protected boolean isDone = false;

    /**
     * Constructor for a Task object.
     * This is only called by inherited classes to set the task description.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns a task's done or undone status.
     * @return Either [X] or [ ] for done and undone respectively.
     */
    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Marks a task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Returns the task description.
     * @return The task description.
     */
    @Override
    public String toString() {
        return description;
    }

    /**
     * Message printed when task is marked as done.
     */
    public void printTaskSetDoneMessage() {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("    " + this.getStatusIcon() + this + "\n");
    }

    /**
     * Message printed when task is marked as undone.
     */
    public void printTaskSetUndoneMessage() {
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("    " + this.getStatusIcon() + this + "\n");
    }

    /**
     * Message printed when task is added to the task list.
     */
    public void printTaskAddedMessage() {
        System.out.println("Got it. I've added this task: ");
        System.out.println("    " + this.getStatusIcon() + this);
        System.out.println("Now you have " + Applemazer.tasks.size() + " tasks in the list. \n");
    }

    /**
     * Message printed when task is deleted from the task list.
     */
    public void printTaskDeletedMessage() {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("    " + this.getStatusIcon() + this);
        System.out.println("Now you have " + Applemazer.tasks.size() + " tasks in the list. \n");
    }
}
