package choaticbot.tasks;

/**
 * The {@code Task} class represents an abstract base class for tasks. It contains common functionality
 * for managing tasks, such as tracking completion status and storing the task's name.
 */
public abstract class Task {

    /**
     * The name of the task.
     */
    private String name;

    /**
     * Boolean flag indicating whether the task is completed.
     */
    private Boolean isCompleted;

    /**
     * Constructs a new {@code Task} with the specified name. The task is initialized as incomplete.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Marks the task as completed.
     */
    public void complete() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void uncomplete() {
        this.isCompleted = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Abstract method that returns the type of the task (e.g., "T" for ToDo, "D" for Deadlines, "E" for Events).
     *
     * @return A string representing the type of the task.
     */
    public abstract String getType();

    /**
     * Abstract method that returns additional information about the task, such as deadlines or event times.
     *
     * @return A string containing additional task information.
     */
    public abstract String getAdditionalInfo();

    /**
     * Checks if the task is marked as completed.
     *
     * @return {@code true} if the task is completed, otherwise {@code false}.
     */
    public boolean isComplete() {
        return this.isCompleted;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone A boolean indicating whether the task is done.
     */
    public void setDone(boolean isDone) {
        this.isCompleted = isDone;
    }

    /**
     * Converts the task to a file-friendly string format.
     * Includes task type, name, completion status, and additional information.
     *
     * @return A string formatted for file storage.
     */
    public String toFileString() {
        return getType() + "|" + getName() + "|" + isComplete() + "|" + getAdditionalInfo();
    }

    /**
     * Checks if the task name contains the specified word.
     *
     * @param name The word to search for in the task's name.
     * @return {@code true} if the name contains the specified word, otherwise {@code false}.
     */
    public boolean containWord(String name) {
        return this.name.contains(name);
    }

    /**
     * Returns a string representation of the task, including its completion status and name.
     *
     * @return A string in the format {@code [X] task_name} if completed, or {@code [ ] task_name} if not.
     */
    @Override
    public String toString() {
        String marker = isCompleted ? "[X]" : "[ ]";
        return marker + " " + this.name;
    }
}
