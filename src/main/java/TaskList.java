import java.io.IOException;
import java.util.ArrayList;

/**
 * TaskList handles adding and listing of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks; // Array to store tasks
    private final Storage storage;       // Storage for saving, loading of tasks

    /**
     * Constructs a new TaskList with a fixed size to store up to 100 tasks.
     *
     */
    public TaskList(Storage storage) throws IOException, SecurityException, MiraException {
        this.storage = storage;
        this.tasks = this.storage.loadTasks();
    }

    /**
     * Adds a new task to the list and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param index The index of the task to delete (1-based).
     */
    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Lists all the tasks currently stored in the task list.
     */
    public String listTasks() {
        StringBuilder tasksList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            tasksList.append((i + 1)).append(". ").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                tasksList.append("\n"); // Add newline only if it is not the last task
            }
        }
        return tasksList.toString();
    }

    /**
     * Marks the specified task as done.
     *
     * @param index The index of the task to mark as done (1-based index).
     */
    public void markTask(int index) {
        this.tasks.get(index - 1).setStatus(true);
    }

    /**
     * Unmarks the specified task, setting it back to not done.
     *
     * @param index The index of the task to unmark (1-based index).
     */
    public void unmarkTask(int index) {
        this.tasks.get(index - 1).setStatus(false);
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int size() {
        return tasks.size();
    }
}
