package duck.data;

import java.util.ArrayList;

import duck.data.exception.DuckException;
import duck.data.task.Task;
import duck.storage.Storage;



/**
 * Represents a list of tasks in the Duck application.
 * This class extends {@link ArrayList} and provides methods for adding and deleting tasks,
 * as well as interacting with the storage system to persist changes.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Adds a task to the task list and updates the storage system.
     *
     * @param task The task to be added to the list.
     * @param storage The storage system used to save the task.
     * @throws DuckException If an error occurs while interacting with the storage system.
     */
    public void addTask(Task task, Storage storage) throws DuckException {
        assert task != null : "Task to be added is null";
        assert storage != null : "Storage not yet initialized";

        this.add(task);
        storage.appendTask(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + this.size() + " tasks in the list.\n");
    }

    /**
     * Deletes a task from the task list by its index and updates the storage system.
     *
     * @param index The index of the task to be deleted.
     * @param storage The storage system used to update the task list.
     * @throws DuckException If an error occurs while interacting with the storage system.
     */
    public void deleteTask(int index, Storage storage) throws DuckException {
        assert index >= 0 && index < this.size();

        System.out.println("Noted. I've removed this task:\n" + this.get(index));
        this.remove(index);
        storage.writeTasks(this);
        System.out.println("Now you have " + this.size() + " tasks in the list.\n");
    }

    /**
     * Finds all the tasks in the task list that contain the specified keyword in description.
     *
     * @param keyword The index of the task to be marked as done.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task t : this) {
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }
}
