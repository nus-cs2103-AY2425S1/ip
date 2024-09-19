package assistinator.tasks;

import assistinator.TaskStatus;

/**
 * Represents todo task.
 */
public class TodoTask extends Task {

    /**
     * Initialises a todo task.
     * @param description Task description.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String toStorageString() {
        return String.format("T | %s | %s", isDone ? TaskStatus.DONE : TaskStatus.NOTDONE, description);
    }
}
