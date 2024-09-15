package zbot.task;

/**
 * Represents a task with a description and completion status.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for ToDo with note.
     *
     * @param description Description of the task.
     * @param note        Note of the task.
     */
    public ToDo(String description, Note note) {
        super(description, note);
    }

    /**
     * Constructor for ToDo with note content.
     *
     * @param description Description of the task.
     * @param note        Note content of the task.
     */
    public ToDo(String description, String note) {
        super(description, note);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
