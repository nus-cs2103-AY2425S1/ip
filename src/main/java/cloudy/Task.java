package cloudy;

/**
 * The Task class represents a task with a description and a completion status. This is an
 * abstract class that provides the basic structure for a specific types of tasks, such as
 * Todo, Deadline, and Event.
 */
public abstract class Task {
    String description;
    boolean isMarked;

    /**
     * Constructs a Task with the specified description and a completion status.
     *
     * @param description The description of the task.
     * @param isMarked The completion status of the task. If true, the task is marked
     *                 as completed.
     */
    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public void markTask() {
        this.isMarked = true;
    }

    public void unmarkTask() {
        this.isMarked = false;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public abstract String printTaskOnList();

    public abstract String toFileFormat();

}
