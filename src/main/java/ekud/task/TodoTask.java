package ekud.task;

import ekud.exceptions.EkudException;

/**
 * Represents a {@link Task} that the user wants to do.
 *
 * @author uniqly
 */
public class TodoTask extends Task {
    private static final String EMPTY_DESCRIPTION_MESSAGE =
        "I think you tried TODO nothing.\nI can only help to remind you of something todo.";
    private static final String SAVE_STRING_FORMAT = "T | %s";
    private static final String STRING_FORMAT = "[T]%s";

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
        return EMPTY_DESCRIPTION_MESSAGE;
    }

    @Override
    public String getSaveTaskString() {
        return String.format(SAVE_STRING_FORMAT, super.getSaveTaskString());
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString());
    }
}
