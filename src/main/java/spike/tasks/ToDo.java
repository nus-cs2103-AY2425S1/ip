package spike.tasks;

/**
 * Represents a task that needs to be done with no deadline.
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo task.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Updates the task based on the update type and updated part.
     *
     * @param updateType  The type of update to be made.
     * @param updatedPart The updated part of the task.
     * @return Task with the updated part.
     */
    public Task updateTask(String updateType, String updatedPart) throws IllegalArgumentException {
        switch (updateType) {
        case "description":
            return new ToDo(updatedPart);
        default:
            throw new IllegalArgumentException("Please enter a valid option");
        }
    }

    /**
     * Returns the task type.
     *
     * @return Task type.
     */
    @Override
    public String getTaskType() {
        return "todo";
    }

    /**
     * Returns the string representation of the task to be saved in the file.
     * Format: T | 0 | description
     *
     * @return String representation of the task to be saved in the file.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

    /**
     * Returns the string representation of the task to be displayed to the user.
     * Format: [T][status] description
     *
     * @return String representation of the task to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
