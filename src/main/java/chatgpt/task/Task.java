package chatgpt.task;

import chatgpt.exception.ChatBotException;

/**
 * The Task class represents the abstract template for all Tasks to follow.
 */
public abstract class Task {
    /** description/task name **/
    private String task;
    /** Status on whether the task has been completed **/
    private boolean isCompleted;

    /**
     * Constructor of new task given the description,
     * whereby default the task is not completed.
     *
     * @param task is the description/task name
     * @throws IllegalArgumentException if the description of the task is empty
     */
    public Task(String task) throws IllegalArgumentException {
        if (task.isEmpty() || task.equals(" ")){
            throw new IllegalArgumentException("\t Oh no!!(0o0) Description cannot be empty");
        }
        this.task = task;
        this.isCompleted = false;
    }

    /**
     * Constructor of new task given the description and completion status.
     *
     * @param task is the description/task name
     * @throws IllegalArgumentException if the description of the task is empty
     */
    public Task(String task, Boolean isCompleted) throws IllegalArgumentException {
        if (task.isEmpty() || task.equals(" ")){
            throw new IllegalArgumentException("\t Oh no!!(0o0) Description cannot be empty");
        }
        this.task = task;
        this.isCompleted = isCompleted;
    }

    /**
     * Sets the completion status of the task to the given boolean.
     *
     * @param isCompleted represents the new completion status of the task
     * @throws ChatBotException if the task already has the same status as the input
     */
    public void setCompleted(boolean isCompleted)
            throws ChatBotException {
        if (this.isCompleted == isCompleted) {
            throw new ChatBotException("\t It seems the task has already been marked as such");
        }
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the task as a String in the appropriate format for displaying.
     *
     * @return task as a string in the appropriate format for displaying
     */
    @Override
    public String toString() {
        return isCompleted ? "[X] " + task : "[ ] " + task;
    }

    /**
     * Returns the task as a String in the appropriate format for saving.
     *
     * @return task as a string in the appropriate format for saving
     */
    public String toPrint() {
        return isCompleted ? "|1|" + task : "|0|" + task;
    }
}
