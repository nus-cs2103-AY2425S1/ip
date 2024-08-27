package tasks;

import applemazer.*;
import java.io.Serializable;

public class Task implements Serializable {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }

    public void printTaskSetDoneMessage() {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("    " + this.getStatusIcon() + this + "\n");
    }

    public void printTaskSetUndoneMessage() {
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("    " + this.getStatusIcon() + this + "\n");
    }

    public void printTaskAddedMessage() {
        System.out.println("Got it. I've added this task: ");
        System.out.println("    " + this.getStatusIcon() + this);
        System.out.println("Now you have " + Applemazer.tasks.size() + " tasks in the list. \n");
    }

    public void printTaskDeletedMessage() {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("    " + this.getStatusIcon() + this);
        System.out.println("Now you have " + Applemazer.tasks.size() + " tasks in the list. \n");
    }
}
