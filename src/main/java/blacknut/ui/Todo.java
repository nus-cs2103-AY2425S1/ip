package blacknut.ui;

/**
 * The Todo class represents a task with no specific deadline or event time.
 */

public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo task to a string format suitable for saving to a file.
     *
     * @return A string representation of the Todo task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }


    /**
     * Returns the string representation of the Todo task.
     *
     * @return A string in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}