package joe.task;

import static joe.Constants.COMPLETED_TASK;
import static joe.Constants.INCOMPLETE_TASK;

abstract public class Task {

    private String task;
    private boolean isDone = false;

    public Task(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        String status = isDone ? COMPLETED_TASK : INCOMPLETE_TASK;
        return String.format("%s %s", status, this.task);
    }

    /**
     * Prints the task with its status.
     */
    public void printTask() {
        String status = isDone ? COMPLETED_TASK : INCOMPLETE_TASK;
        System.out.printf("%s %s", status, this.task);
    }

    /**
     * Toggles the done status of the task.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * @return The done status of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * @return The task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * @return A string representation of the task for saving.
     */
    abstract public String toSaveString();
}
