package stobberi.task;

/**
 * The {@code Task} class represents a task with a description and a status indicating
 * whether it is done or not. It provides methods to mark the task as done or not done,
 * retrieve the task's description, and get a string representation of the task.
 */
public class Task {
    /**
     * The description of the task.
     */
    private final String description;

    /**
     * The status of the task; {@code true} if the task is done, {@code false} otherwise.
     */
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task's description contains the specified word.
     *
     * @param word the word to check for in the description
     * @return {@code true} if the description contains the word, {@code false} otherwise
     */
    public boolean hasWord(String word) {
        return description.contains(word);
    }
    /**
     * Returns the status icon of the task.
     *
     * @return {@code "X"} if the task is done, otherwise returns a single space {@code " "}.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done by setting its status to {@code true}.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting its status to {@code false}.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    public boolean isSame(String description) {
        return this.description.equals(description);
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
