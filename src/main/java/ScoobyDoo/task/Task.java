package ScoobyDoo.task;

/**
 * Represents a task with a description and a completion status.
 */
public abstract class Task {
    private final String description;
    private boolean done;

    /**
     * Create task with description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task (String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Checks if the task's description contains the specified target word.
     *
     * @param targetWord The word to search for within the task's description.
     * @return true if the description contains the target word, false otherwise.
     */
    public boolean find(String targetWord) {
        return description.contains(targetWord);
    }


    /**
     * Returns a string representation of the task in a format suitable for file storage.
     * The format is: "doneStatus | description", where doneStatus is 1 if the task is done, 0 otherwise.
     *
     * @return A string representation of the task for file storage.
     */
    public String toFileFormatString() {
        return String.format("%d | %s", done? 1 : 0, description);
    }

    /**
     * Returns a string representation of the task, including its status and description.
     * The format is: "[statusIcon] description", where statusIcon is "X" if the task is done, " " otherwise.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return String.format("[%s] %s",this.getStatusIcon(), description);
    }


    public void markAsDone() {
        done = true;
    }

    public void markAsUndone() {
        done = false;
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

}
