package torne.task;

import torne.exception.TorneInvalidCommandException;
import torne.exception.TorneInvalidDataException;

/**
 * torne.task.Task with no date/time attached.
 */
public class TaskTodo extends Task {
    private TaskTodo(String name) throws TorneInvalidCommandException, TorneInvalidDataException {
        super(name);
    }

    public static TaskTodo fromStorage(String name) throws TorneInvalidCommandException, TorneInvalidDataException {
        return new TaskTodo(name);
    }

    public static TaskTodo fromCommand(String name) throws TorneInvalidCommandException, TorneInvalidDataException {
        return new TaskTodo(name);
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";

        // format based on the toString output of `torne.task.Task`
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toStorageString() {
        return String.format(
                "%s / %d / %s",
                "T",
                isDone ? 1 : 0,
                name);
    }
}
