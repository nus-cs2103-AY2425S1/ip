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

    /**
     * Creates a list to manage tasks.
     */
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
     * \
     * @param  index       the 0-based index of the task to delete
     * @return             the deleted task
     * @throws IOException if the tasks cannot be saved
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
     * @return             the marked task
     * @throws IOException if the tasks cannot be saved
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
     * @return             the marked task
     * @throws IOException if the tasks cannot be saved
     */
    public Task unmarkTask(int index) throws IOException {
        tasks.get(index).setCompletion(false);
        saveTasks();
        return tasks.get(index);
    }

    /**
     * Loads stored tasks from disk.
     *
     * @return                      the list of stored tasks
     * @throws InvalidTaskException if there are errors in parsing the stored task
     * @throws IOException          if the stored file cannot be read
     */
    private List<Task> loadTasks() throws IOException, InvalidTaskException {
        return storage.loadTasks();
    }

    /**
     * Finds tasks which contain a given keyword.
     *
     * @param  keyword the keyword to search for
     * @return         the tasks which contain the keyword
     */
    public List<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter((task) -> task.containsKeyword(keyword))
                .toList();
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
