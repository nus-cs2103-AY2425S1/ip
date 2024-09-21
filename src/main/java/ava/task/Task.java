package ava.task;

/**
 * Represents a task
 */
abstract public class Task {
    private String title;
    private boolean isDone;
    private Priority priority;

    /**
     * Creates a new task with title
     *
     * @param title the title
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Creates a new task with title and completion status
     *
     * @param title the title
     * @param isDone the completion status
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Returns the title of the task
     *
     * @return the title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the completion status of the task
     *
     * @return the completion status of the task
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a string
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return String.format("%s | %s", this.isDone?"✅ Done   ":"❌ Pending",title);
    }

    /**
     * Serializes the task to a string
     *
     * <br>
     * Optimizes the task's string representation for storage
     * @return the serialized string
     */
    public String serialize() {
        return String.format("%s,%s", this.isDone?"1":"0",title);
    }
}
