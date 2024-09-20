package jbot.task;

/**
 * Represents an abstract task with a name and a completion status.
 * Subclasses should specify the type of task and its specific behavior.
 */
public abstract class Task {

    private String name;
    private boolean done = false;
    private String taskTypeSymbol;

    /**
     * Returns whether the task is marked as done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.done = false;
    }

    /**
     * Returns a string representation of the task.
     * The format is "[taskTypeSymbol] [doneStatus] taskName".
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String doneString = this.done ? "X" : " ";
        return String.format("[%1$s] [%2$s]  %3$s", this.getTaskTypeSymbol(), doneString, this.getName());
    }

    /**
     * Returns the symbol representing the type of task.
     *
     * @return The task type symbol.
     */
    public String getTaskTypeSymbol() {
        return this.taskTypeSymbol;
    }

    /**
     * Returns the name of the task.
     *
     * @return The task name.
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaskTypeSymbol(String taskTypeSymbol) {
        this.taskTypeSymbol = taskTypeSymbol;
    }
}
