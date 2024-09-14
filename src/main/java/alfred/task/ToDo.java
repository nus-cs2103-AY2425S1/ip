package alfred.task;

import java.util.List;

/**
 * Represents a task of type "To-Do". This class extends the <code>Task</code> class
 * and provides specific functionality for tasks that do not have a fixed deadline
 * or event time.
 */
public class ToDo extends Task {
    /**
     * Constructs a new <code>ToDos</code> task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new <code>ToDos</code> task with the specified description, completion status, and tags.
     *
     * @param description Description of the task.
     * @param isDone The completion status of the task.
     * @param tags The list of tags for the task (a copy of the provided list will be made to avoid reference issues).
     */
    public ToDo(String description, boolean isDone, List<String> tags) {
        super(description, isDone, tags);
    }

    /**
     * Returns a string representation of the <code>ToDos</code> task.
     * The format includes the task type indicator "[T]" followed by the task details.
     *
     * @return A string representing the <code>ToDos</code> task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the file format representation of the <code>ToDos</code> task.
     * The format is "T | status | description", where "status" is the task's
     * completion status and "description" is the task's description.
     *
     * @return A string representing the <code>ToDos</code> task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + getTagsAsString() + " | " + description;
    }
}
