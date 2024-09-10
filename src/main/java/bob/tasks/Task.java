package bob.tasks;

/**
 * Task is an abstract class
 * It stores the task name and the status of the task
 */
public abstract class Task {
    private final String taskName;
    private boolean isCompleted;

    /**
     * Constructor for Task
     * Sets the status of isCompleted to be false by default
     *
     * @param taskName The name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Sets the status of isCompleted to be true to indicate that the task has been completed
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Sets the status of isCompleted to be false to indicate that the task has not been completed.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Check if the given string is contained in the Task Name
     *
     * @param searchString String to search for in the task name
     * @return If the string is in the task name
     */
    public boolean contains(String searchString) {
        return this.taskName.toUpperCase().contains(searchString.toUpperCase());
    }


    /**
     * Exports the Task object to string to be saved in a text file
     *
     * @return String format of the Task object to be exported
     */
    public String export() {
        return String.format("%s %s", this.isCompleted, this.taskName);
    }

    /**
     * Converts the Task object to a string to be printed
     *
     * @return String format of the Task object for printing
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isCompleted ? "X" : " ", this.taskName);
    }
}
