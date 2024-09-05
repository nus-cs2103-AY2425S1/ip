package torne.task;

import torne.exception.TorneInvalidCommandException;
import torne.exception.TorneInvalidDataException;
import torne.util.TorneDateTime;

/**
 * torne.task.Task with a deadline.
 */
public class TaskDeadline extends Task {
    protected TorneDateTime by;

    private TaskDeadline(String name, TorneDateTime by) throws TorneInvalidCommandException, TorneInvalidDataException {
        super(name);

        this.by = by;
    }

    public static TaskDeadline fromStorage(String name, String by) throws TorneInvalidDataException,
            TorneInvalidCommandException {
        if (by == null || by.isBlank()) {
            throw new TorneInvalidDataException("Datetime by is empty");
        }

        return new TaskDeadline(
                name,
                TorneDateTime.parseStorageString(by.trim())
        );
    }

    public static TaskDeadline fromCommand(String name, String by) throws TorneInvalidCommandException,
            TorneInvalidDataException {
        if (by == null || by.isBlank()) {
            throw new TorneInvalidCommandException("Option /by cannot be empty");
        }

        return new TaskDeadline(
                name,
                TorneDateTime.parseInputDateTimeString(by.trim())
        );
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";

        // format based on the toString output of `torne.task.Task`
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                by.toRelativeString());
    }

    @Override
    public String toStorageString() {
        return String.format(
                "%s / %d / %s / %s",
                "D",
                isDone ? 1 : 0,
                name,
                by.toStorageString());
    }
}
