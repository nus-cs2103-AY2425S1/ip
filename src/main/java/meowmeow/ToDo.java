package meowmeow;

/**
 * Represents a task without any date associated with it
 */
class ToDo extends Task {

    /**
     * Constructs a To Do task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the To Do task, including the task type,
     * status and description.
     *
     * @return A string representation of the To Do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the To Do task into a format suitable for saving to a file.
     *
     * @return A string representing the To Do task in a file format.
     */
    @Override
    public String convertToFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}