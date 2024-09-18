package nether.task;

/**
 * Represents a TodoTask, a type of Task that only has a description and status. No timestamps.
 * The TodoTask class inherits the Task class
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask object.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description, String tag) {
        super(description, tag);
    }

    /**
     * Returns the string representation of the TodoTask in the format desired to save into a data file.
     * The format is: T|status|description, where T indicates a TodoTask.
     *
     * @return A string in the format "T|status|description".
     */
    @Override
    public String toSaveFormat() {
        return "T|" + this.getStatusIcon() + "|" + this.getTag() + "|" + this.getDescription();
    }

    /**
     * Returns the string representation of the TodoTask.
     * The format is: [T][status] description, where [T] indicates a TodoTask.
     *
     * @return A string representation of the TodoTask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
