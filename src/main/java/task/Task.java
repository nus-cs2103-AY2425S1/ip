package task;

import exceptions.AlreadyCompletedException;
import exceptions.InvalidDataFormatException;
import exceptions.StartAfterEndException;

/**
 * Abstract class representing a task stored by Him.
 *
 * @author IsaacPangTH
 */
public abstract class Task {

    private boolean isCompleted = false;
    private String description;

    /**
     * Constructor for a <code>Task</code>.
     *
     * @param description Description of the task.
     * @param isCompleted Completion status of the task.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Factory method which creates a <code>Task</code>from a String containing data of the task.
     *
     * @param data String containing task information. (See <code>ToDo.toData()</code>, <code>Deadline.toData()</code>,
     * <code>Event.toData()</code> for format details).
     * @return <code>Task</code> from String.
     * @throws StartAfterEndException If invalid task if parsed.
     */
    public static Task of(String data) throws StartAfterEndException, InvalidDataFormatException {
        try {
            String[] args = data.split("\\|", 2);
            //@formatter:off
            String taskTypeIcon = args[0];
            String taskData = args[1];
            return switch (taskTypeIcon) {
            case "T" -> ToDo.of(taskData);
            case "D" -> Deadline.of(taskData);
            case "E" -> Event.of(taskData);
            default -> throw new InvalidDataFormatException();
            };
            //@formatter:on
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDataFormatException();
        }
    }

    /**
     * Marks the task as completed.
     *
     * @throws AlreadyCompletedException If task has already been completed.
     */
    public void complete() throws AlreadyCompletedException {
        if (this.isCompleted) {
            throw new AlreadyCompletedException();
        }
        this.isCompleted = true;
    }

    /**
     * Returns a string indicating completion status.
     *
     * @return Status icon (X if completed, space otherwise).
     */
    public String getStatusIcon() {
        return (this.isCompleted ? "X" : " ");
    }

    /**
     * Returns a string indicating subtype of task.
     *
     * @return Type icon (T for <code>ToDo</code>, D for <code>Deadline</code>, E for <code>Event</code>).
     */
    public abstract String getTypeIcon();

    /**
     * Returns data of the task in string form.
     *
     * @return Task data in the format "Type|completion status|description".
     */
    public String toData() {
        assert !this.description.isEmpty() : "Description should not be empty";
        return String.format("%s|%b|%s", this.getTypeIcon(), this.isCompleted, this.description);
    }

    /**
     * Returns <code>true</code> if task's description contains the keyword.
     *
     * @param keyword Keyword to check for.
     * @return <code>true</code>if task's description contains the keyword.
     */
    public boolean descriptionContains(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return this.description;
    }
}

