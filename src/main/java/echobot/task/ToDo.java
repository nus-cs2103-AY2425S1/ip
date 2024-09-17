package echobot.task;

import echobot.exception.TaskNameEmptyException;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {
    public ToDo(Boolean isDone, String taskName) throws TaskNameEmptyException {
        super(isDone, taskName);
    }

    @Override
    public String save() {
        return "T " + super.save();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
