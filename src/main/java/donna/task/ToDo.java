package donna.task;

import donna.DonnaException;

/**
 * Represents a ToDo task which includes a description.
 */
public class ToDo extends Task {
    private final String description;

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the task.
     * @throws DonnaException If the description is empty.
     */
    public ToDo(String description) throws DonnaException {
        super(description);
        this.description = description;
    }

    /**
     * Returns a string representation of the to-do task in a format suitable for saving to a file.
     *
     * @return A string representing the to-do task in the format "T | done status | description".
     */
    @Override
    public String toFileFormat() {
        return "T | " + (this.isDone() ? "1" : "0") + " | " + description;
    }

    @Override
    public String getType() {
        return "T";
    }
}
