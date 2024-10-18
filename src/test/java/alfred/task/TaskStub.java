package alfred.task;

/**
 * A stub class for testing Task-related functionalities.
 * This class is a simplified version of the Task class, used specifically for testing purposes.
 */
public class TaskStub extends Task {

    /**
     * Constructs a TaskStub with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public TaskStub(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task in the format used for file storage.
     *
     * @return A string that represents the task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "X" : " ") + " | " + description;
    }
}
