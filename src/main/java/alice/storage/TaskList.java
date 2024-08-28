package alice.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alice.task.InvalidTaskException;
import alice.task.Task;

/** Container for tasks. */
public class TaskList {
    private final Storage storage;
    private List<Task> tasks;

    public TaskList(Storage storage) {
        this.storage = storage;
        try {
            this.tasks = loadTasks();
        } catch (IOException | InvalidTaskException e) {
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Basic getter for tasks.
     * 
     * @return the current list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to list and saves to disk.
     * 
     * @param  task        the task to add
     * @throws IOException if the tasks cannot be saved
     */
    public void addTask(Task task) throws IOException {
        tasks.add(task);
        saveTasks();
    }

    /**
     * Deletes a task from the list and saves to disk.
     * 
     * @param  index       the 0-based index of the task to delete
     * @throws IOException if the tasks cannot be saved
     * @return             the deleted task
     */
    public Task deleteTask(int index) throws IOException {
        Task removedTask = tasks.remove(index);
        saveTasks();
        return removedTask;
    }

    /**
     * Marks a task as complete and saves to disk.
     * 
     * @param  index       the 0-based index of the task to mark
     * @throws IOException if the tasks cannot be saved
     * @return             the marked task
     */
    public Task markTask(int index) throws IOException {
        tasks.get(index).setCompletion(true);
        saveTasks();
        return tasks.get(index);
    }

    /**
     * Marks a task as incomplete and saves to disk.
     * 
     * @param  index       the 0-based index of the task to unmark
     * @throws IOException if the tasks cannot be saved
     * @return             the marked task
     */
    public Task unmarkTask(int index) throws IOException {
        tasks.get(index).setCompletion(false);
        saveTasks();
        return tasks.get(index);
    }

    /**
     * Loads stored tasks from disk.
     * 
     * @throws IOException          if the stored file cannot be read
     * @throws InvalidTaskException if there are errors in parsing the stored task
     * @return                      the list of stored tasks
     */
    private List<Task> loadTasks() throws IOException, InvalidTaskException {
        return storage.loadTasks();
    }

    /**
     * Save tasks to disk to persist data.
     * 
     * @throws IOException if the stored file cannot be written to
     */
    private void saveTasks() throws IOException {
        storage.saveTasks(tasks);
    }
}