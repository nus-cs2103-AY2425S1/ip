package michael;

/**
 * Represents a generic task
 */
public class Task implements Comparable<Task> {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Represents task as done.
     *
     */
    public void doTask() {
        this.isDone = true;
    }

    /**
     * Represents task as not done yet.
     *
     */
    public void undoTask() {
        this.isDone = false;
    }

    /**
     * Returns true if task is done, and false otherwise.
     *
     * @return Boolean value corresponding to status of task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Compares this task to another according to their alphabetical order.
     *
     * @param other the task to be compared.
     * @return A negative integer if this task comes before the other alphabetically, 0 if equal,
     * or a positive integer if this task follows the other alphabetically.
     */
    @Override
    public int compareTo(Task other) {
        String myName = this.taskName;
        String yourName = other.taskName;

        return myName.compareToIgnoreCase(yourName); // Use built in String compare method, ignoring case
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }
}
