package michael;

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
