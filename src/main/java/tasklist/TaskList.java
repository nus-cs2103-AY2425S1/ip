package tasklist;

import java.util.ArrayList;
import java.util.List;

import command.CommandNotFoundException;
import ouiouibaguette.OuiOuiBaguetteException;
import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Represents a list of tasks and provides methods to manipulate and access these tasks.
 * The tasks are loaded from and stored in a given storage system.
 */
public class TaskList {

    /** The storage system used to persist the tasks. */
    protected Storage storage;

    /** The list that holds all the tasks. */
    protected List<Task> tasklist;

    /**
     * Constructs a TaskList with the specified storage system.
     * Loads the tasks from the storage into the task list.
     *
     * @param storage The storage system used to persist the tasks.
     */
    public TaskList(Storage storage) {
        // Check if storage is null
        assert storage != null : "Storage argument cannot be null";

        this.storage = storage;
        tasklist = new ArrayList<Task>();

        // Load data from storage
        List<String> data = storage.load();

        for (String line : data) {
            Task task = parseTaskFromDataFormat(line);

            // Add task to task list
            if (task != null) {
                tasklist.add(task);
            }
        }
    }


    /**
     * Parses the data format of a task.
     * Creates appropriate Task object.
     *
     * @param line Data format of a task
     */
    public Task parseTaskFromDataFormat(String line) {
        Task task = null;

        try {
            // Escape |
            String[] taskParts = line.split(" \\| ");

            if (taskParts[0].equals("todo")) {
                task = new ToDo(taskParts[2]);
                if (Integer.parseInt(taskParts[1]) == 1) {
                    task.mark();
                }
            } else if (taskParts[0].equals("deadline")) {
                task = new Deadline(taskParts[2], taskParts[3]);
                if (Integer.parseInt(taskParts[1]) == 1) {
                    task.mark();
                }
            } else if (taskParts[0].equals("event")) {
                task = new Event(taskParts[2], taskParts[3], taskParts[4]);
                if (Integer.parseInt(taskParts[1]) == 1) {
                    task.mark();
                }
            } else {
                throw new CommandNotFoundException("Command not found: " + taskParts[0]);
            }

        } catch (OuiOuiBaguetteException e) {
            // Handle the exception (can log this if needed)
            e.printStackTrace();
        }

        return task;
    }

    /**
     * Adds a task to the task list and updates the storage.
     *
     * @param task The task to be added.
     * @return The task that was added.
     */
    public Task addTask(Task task) {
        // Check if task is null
        assert task != null : "Task cannot be null";

        tasklist.add(task);
        storage.store(task.toDataFormat());
        return task;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws TaskListOutOfBoundsException If the index is out of bounds.
     */
    public Task getTask(int index) throws TaskListOutOfBoundsException {
        if (index < 0 || index >= tasklist.size()) {
            throw new TaskListOutOfBoundsException(index + 1, size());
        }
        return tasklist.get(index);
    }

    /**
     * Marks the task at the specified index as done and updates the storage.
     *
     * @param index The index of the task to mark as done.
     * @return The task that was marked as done.
     * @throws TaskListOutOfBoundsException If the index is out of bounds.
     */
    public Task mark(int index) throws TaskListOutOfBoundsException {
        if (index < 0 || index >= tasklist.size()) {
            throw new TaskListOutOfBoundsException(index + 1, size());
        }

        Task task = tasklist.get(index);
        task.mark();

        storage.update(toDataFormat());

        return task;
    }

    /**
     * Unmarks the task at the specified index as not done and updates the storage.
     *
     * @param index The index of the task to unmark.
     * @return The task that was unmarked.
     * @throws TaskListOutOfBoundsException If the index is out of bounds.
     */
    public Task unmark(int index) throws TaskListOutOfBoundsException {
        if (index < 0 || index >= tasklist.size()) {
            throw new TaskListOutOfBoundsException(index + 1, size());
        }

        Task task = tasklist.get(index);
        task.unmark();

        storage.update(toDataFormat());

        return task;
    }

    /**
     * Deletes the task at the specified index from the task list and updates the storage.
     *
     * @param index The index of the task to delete.
     * @return The task that was deleted.
     * @throws TaskListOutOfBoundsException If the index is out of bounds.
     */
    public Task delete(int index) throws TaskListOutOfBoundsException {
        if (index < 0 || index >= tasklist.size()) {
            throw new TaskListOutOfBoundsException(index + 1, size());
        }

        Task task = tasklist.remove(index);
        storage.update(toDataFormat());

        return task;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasklist.size();
    }

    /**
     * Returns the list of tasks in the task list.
     *
     * @return The list of tasks in the task list.
     */
    public List<Task> getTasks() {
        return tasklist;
    }

    /**
     * Converts the tasks in the task list to a format suitable for storage.
     *
     * @return A list of strings representing the tasks in a format suitable for storage.
     */
    public List<String> toDataFormat() {
        List<String> res = new ArrayList<>();
        for (Task t : tasklist) {
            res.add(t.toDataFormat());
        }
        return res;
    }
}
