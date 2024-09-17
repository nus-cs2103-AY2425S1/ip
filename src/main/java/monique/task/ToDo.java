package monique.task;

/**
 * The <code>ToDo</code> class represents a specific type of task that does not have any associated dates.
 * It extends the <code>Task</code> class and includes only a description and completion status.
 */
public class ToDo extends Task {
    private static final String FORMATSTRING = "[T][%s] %s";

    /**
     * Constructs a new <code>ToDo</code> object with the specified description and sets the task as incomplete.
     *
     * @param description The description of the <code>ToDo</code> task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new <code>ToDo</code> object with the specified description and completion status.
     *
     * @param description The description of the <code>ToDo</code> task.
     * @param isComplete The completion status of the <code>ToDo</code> task.
     */
    public ToDo(String description, boolean isComplete) {
        super(description, isComplete);
    }


    /**
     * Constructs a new <code>ToDo</code> object with an empty description and sets the task as complete by default.
     */
    public ToDo() {
        this("", true);
    }

    /**
     * Returns a string representation of the <code>ToDo</code> task.
     * The format is: "[T][status] description" where status is "X" if the task is complete.
     *
     * @return A string representation of the <code>ToDo</code> task.
     */
    @Override
    public String toString() {
        return String.format(FORMATSTRING, this.isComplete() ? "X" : " ", this.getDescription());
    }

    /**
     * Marks the <code>ToDo</code> task as complete and returns a new instance of the <code>ToDo</code> task
     * with the updated status.
     *
     * @return A new <code>ToDo</code> object with the status marked as complete.
     */
    @Override
    public ToDo mark() {
        return new ToDo(this.getDescription(), true);
    }

    /**
     * Unmarks the <code>ToDo</code> task as incomplete and returns a new instance of the <code>ToDo</code> task
     * with the updated status.
     *
     * @return A new <code>ToDo</code> object with the status marked as incomplete.
     */
    @Override
    public ToDo unmark() {
        return new ToDo(this.getDescription(), false);
    }



}
