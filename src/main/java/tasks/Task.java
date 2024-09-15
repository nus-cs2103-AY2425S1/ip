package tasks;

/**
 * Represents an abstract task with a name and a completion status.
 * This class provides common functionality for various types of tasks.
 * The task is either marked as done or not done.
 */
public abstract class Task {
    private final String name;
    private boolean isDone;

    /**
     * Constructs a Task with the specified name.
     * The task is initially not done.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     * The format is "[x] taskName" if the task is done, and "[ ] taskName" if not.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[x] " + this.name;
        }
        return "[ ] " + this.name;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the task's name contains the specified word (case-insensitive).
     * The search is performed in a case-insensitive manner, converting both the
     * task's name and the input word to lowercase before checking.
     *
     * @param word the word to search for within the task's name
     * @return true if the task's name contains the specified word, false otherwise
     */
    public boolean containWord(String word) {
        return this.name.contains(word.toLowerCase());
    }

    /**
     * Compares the task's name to a specified string.
     * This method overrides the equals method to allow comparison between the
     * task's name and a string directly. The comparison is case-insensitive.
     *
     * @param obj the object to be compared, expected to be a string
     * @return true if the task's name equals the specified string, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String str) {
            return this.name.equals(str.toLowerCase());
        }
        return false;
    }
}
