package futureyou.task;

/**
 * Represents a task with a name and completion status.
 */
public class Task {
    private boolean completed = false;
    private final String name;

    /**
     * Constructs a Task with the specified name.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.name = taskName;
    }

    /**
     * Constructs a Task with the specified name and completion status.
     *
     * @param taskName  The name of the task.
     * @param completed The completion status of the task.
     */
    public Task(String taskName, boolean completed) {
        this.name = taskName;
        this.completed = completed;
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        this.completed = true;
    }

    /**
     * Returns the name of the task.
     *
     * @return The task's name.
     */
    public String getTaskName() {
        return this.name;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return task completion status (Boolean).
     */
    public boolean getTaskStatus() {
        return this.completed;
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task, which is "T" for a basic task.
     */
    public String getType() {
        return "T";
    }

    /**
     * Returns a string representation of the task formatted for storage.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        // The format is: "T|<done>|<taskName>", where <done> is 1 if completed, 0 otherwise.
        int done = (this.getTaskStatus() ? 1 : 0);
        return this.getType() + "|" + done + "|" + this.getTaskName();
    }

    /**
     * Returns a formatted string representation of the task for display.
     *
     * @return A formatted string for displaying the task.
     */
    public String print() {
        //  The format is: "[T] [X] <taskName>", where [X] is marked if completed.
        String cross = (this.getTaskStatus() ? "X" : " ");
        return "[" + this.getType() + "] " + "[" + cross + "] " + this.getTaskName();
    }
}
