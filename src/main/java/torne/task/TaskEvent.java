package torne.task;

import torne.exception.TorneInvalidCommandException;
import torne.exception.TorneInvalidDataException;
import torne.util.TorneDateTime;

/**
 * torne.task.Task that starts and ends at specific datetimes.
 */
public class TaskEvent extends Task {
    protected TorneDateTime from;
    protected TorneDateTime to;

    private TaskEvent(String name, TorneDateTime from, TorneDateTime to) throws TorneInvalidCommandException {
        super(name);

        this.from = from;
        this.to = to;

    }

    public static TaskEvent fromStorage(String name, String from, String to) throws TorneInvalidDataException,
            TorneInvalidCommandException {
        if (from == null || to == null || from.isBlank() || to.isBlank()) {
            throw new TorneInvalidDataException("Datetimes from or to are empty");
        }

        return new TaskEvent(
                name,
                TorneDateTime.parseStorageString(from.trim()),
                TorneDateTime.parseStorageString(to.trim())
        );
    }

    public static TaskEvent fromCommand(String name, String from, String to) throws TorneInvalidDataException,
            TorneInvalidCommandException {
        if (from == null || to == null || from.isBlank() || to.isBlank()) {
            throw new TorneInvalidCommandException("Options /from and /to cannot be empty");
        }

        return new TaskEvent(
                name,
                TorneDateTime.parseInputDateTimeString(from.trim()),
                TorneDateTime.parseInputDateTimeString(to.trim())
        );
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";

        // format based on the toString output of `torne.task.Task`
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                from.toRelativeString(),
                to.toRelativeString());
    }

    @Override
    public String toStorageString() {
        return String.format(
                "%s / %d / %s / %s / %s",
                "E",
                isDone ? 1 : 0,
                name,
                from.toStorageString(),
                to.toStorageString());
    }
}
