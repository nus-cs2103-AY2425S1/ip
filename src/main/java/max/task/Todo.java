package max.task;

/**
 * The Todo class represents a task that needs to be done,
 * without any specific deadline or time frame.
 * It is a subclass of the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task,
     * including its status and description.
     * The format is "[T][status] description", where the status is either "X" (done) or " " (not done).
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the task in a format suitable for saving to a file.
     * The format includes the task type, completion status, description,
     * and any tags associated with the task.
     *
     * @return A string representing the task formatted for file storage.
     */
    @Override
    public String toFileFormat() {
        String tagsString = getTagsString();
        return String.format("T | %d | %s%s", isDone ? 1 : 0, description,
                tagsString.isEmpty() ? "" : " | " + tagsString);
    }
}
