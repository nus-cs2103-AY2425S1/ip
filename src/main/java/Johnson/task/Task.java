package Johnson.task;

/**
 * Represents a task with a name and completion status.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructs a Task with the specified name.
     *
     * @param taskName the name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructs a Task with no name.
     */
    public Task() {
        this.taskName = null;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified name and completion status.
     *
     * @param taskName the name of the task.
     * @param isDone the completion status of the task.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Marks the task as complete.
     *
     * @return true if the task is marked as complete.
     */
    public boolean completeTask() {
        isDone = true;
        return isDone;
    }

    /**
     * Marks the task as incomplete.
     *
     * @return true if the task is marked as incomplete.
     */
    public boolean uncompleteTask() {
        isDone = false;
        return isDone;
    }
    public String getTaskName() {
        return taskName;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskStatus(boolean bool) {
        isDone = bool;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        String res;
        if (isDone) {
            res = "[X] ";
        }
        else {
            res = "[ ] ";
        }
        return res + taskName;
    }
}
