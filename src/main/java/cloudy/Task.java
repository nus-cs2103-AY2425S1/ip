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


    /**
     * Marks a task as completed by setting the isMarked attribute to true.
     */
    public void markTask() {
        this.isMarked = true;
    }

    /**
     * Marks a task as not completed by setting the isMarked attribute to false.
     */
    public void unmarkTask() {
        this.isMarked = false;
    }

    /**
     * Checks if a task is marked as completed.
     *
     * @return true if the task is marked as completed, otherwise false.
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Abstract method to print the task in a format suitable for a list display.
     * Each subclass of Task must implement this method to define how the task should
     * be represented in a task list.
     *
     * @return A string representing the task in a list format.
     */
    public abstract String printTaskOnList();

    /**
     * Abstract method to represent the task in a form suitable for file storage.
     * Each subclass of Task must implement this method to define how the task should be saved
     * to a file
     *
     * @return A string representing the task in a file format. 
     */
    public abstract String toFileFormat();

}
