package momo.task;

/**
 * This class represents all task objects that have been created,
 * which have a {@code isComplete} property which can be toggled
 * on and off via the {@code markComplete} and {@code unmark} methods
 */

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

    /**
     * Marks the task object as complete
     */
    public void markComplete() {
        this.isComplete = true;
    }

    /**
     * Marks task object as incomplete
     */
    public void unmark() {
        this.isComplete = false;
    }

    public String toFileString() {
        return isComplete ? "0|" + task : "1|" + task;
    }

    @Override
    public String toString() {
        return isComplete ? "[X] " + task + " " : "[ ] " + task + " ";
    }
}
