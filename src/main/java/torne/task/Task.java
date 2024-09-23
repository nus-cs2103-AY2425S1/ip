package torne.task;

import torne.exception.TorneInvalidCommandException;
import torne.exception.TorneInvalidDataException;

/**
 * Class that encapsulates a task that can be added to `torne.ui.Torne`.
 */
public class Task {
    protected final String name;
    protected boolean isDone;

    /**
     * Creates a torne.task.Task object from a given storage string.
     *
     * @return torne.task.Task created from the input string.
     * @throws TorneInvalidCommandException for issues with loading the torne.task.Task via command.
     * @throws TorneInvalidDataException    for issues with loading the torne.task.Task via storage.
     */
    public static Task fromStorageString(String input) throws TorneInvalidCommandException, TorneInvalidDataException {
        assert input != null : "input should not be null";

        String[] parts = input.split(" / ");
        if (parts.length < 3) {
            // min length is 3. If less than that, raise error
            throw new TorneInvalidDataException("Input task string does not have enough components");
        }

        boolean isTaskDone;
        Task newTask;

        // parse second component "1" / "0" for completion status
        switch (parts[1]) {
        case "1":
            isTaskDone = true;
            break;
        case "0":
            isTaskDone = false;
            break;
        default:
            throw new TorneInvalidDataException("Completion status is invalid.");
        }

        // parse based on type
        switch (parts[0]) {
        case "T":
            // torne.task.TaskTodo
            if (parts.length != 3) {
                throw new TorneInvalidDataException("Invalid todo storage string.");
            }
            newTask = TaskTodo.fromStorage(parts[2]);
            break;
        case "D":
            // torne.task.TaskDeadline
            if (parts.length != 4) {
                throw new TorneInvalidDataException("Invalid deadline storage string.");
            }
            newTask = TaskDeadline.fromStorage(parts[2], parts[3]);
            break;
        case "E":
            // torne.task.TaskDeadline
            if (parts.length != 5) {
                throw new TorneInvalidDataException("Invalid event storage string.");
            }
            newTask = TaskEvent.fromStorage(parts[2], parts[3], parts[4]);
            break;
        default:
            throw new TorneInvalidDataException("Storage string task type code invalid.");
        }

        // set completion status
        newTask.isDone = isTaskDone;
        return newTask;
    }

    protected Task(String name) throws TorneInvalidCommandException {
        if (name == null || name.isEmpty()) {
            throw new TorneInvalidCommandException("Task cannot have an empty name");
        }
        this.name = name.trim();
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";

        return String.format("[%s] %s", status, name);
    }

    /**
     * Returns a string used to store the task in a local file.
     *
     * @return String representation of task used for local storage.
     */
    public String toStorageString() {
        return name;
    }
}
