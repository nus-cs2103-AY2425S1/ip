package ekud.task;

import ekud.exceptions.EkudException;

/**
 * Represents a {@link Task} that the user wants to do.
 *
 * @author uniqly
 */
public class TodoTask extends Task {
    /**
     * Constructs a {@link Task} the user wants to do.
     *
     * @param description The description of the task.
     * @throws EkudException If the description is empty.
     */
    public TodoTask(String description) throws EkudException {
        super(description);
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return "I think you tried TODO nothing.\nI can only help to remind you of something todo.";
    }

    @Override
    public String getSaveTaskString() {
        return String.format("T | %s", super.getSaveTaskString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
