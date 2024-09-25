package fishman.task;

/**
 * Represents a task for the fishman bot.
 * This class contains the details of a task.
 */
public abstract class Task {
    protected String taskDescription;
    protected boolean isTaskDone;

    /**
     * Constructs a new Task object with the given detail and completion indicator.
     *
     * @param taskDescription The details of the task.
     */
    public Task(String taskDescription, boolean isTaskDone) {
        this.taskDescription = taskDescription;
        this.isTaskDone = isTaskDone;
    }

    /**
     * Determines the completion status of the task.
     *
     * @return true or false depending on the task completion .
     */
    public boolean getTaskStatus() {
        return isTaskDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Marks the task as done.
     * This method sets the 'isDone' status of the task to true.
     */
    public void markAsDone() {
        this.isTaskDone = true;
    }

    /**
     * Marks the task as not done
     * This method sets the 'isDone' status of the task to false.
     */
    public void markAsNotDone() {
        this.isTaskDone = false;
    }

    public abstract String getTaskType();


    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + (isTaskDone ? "X" : " ") + "] " + taskDescription;
    }
}
