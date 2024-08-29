import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Handles task operations including adding, marking, unmarking, deleting, and listing tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList instance with the given storage file path.
     *
     * @param filePath The path to the storage file.
     */
    public TaskList(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Adds a new task to the list and saves the updated list.
     *
     * @param task The task to be added.
     * @throws AngelException If there is an error while saving tasks.
     */
    public void addTask(Task task) throws AngelException {
        tasks.add(task);
        saveTasks();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked.
     * @throws AngelException If the task does not exist or if there is an error while saving tasks.
     */
    public void markTask(int index) throws AngelException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException("Task not found: " + (index + 1));
        }
        tasks.get(index).markAsDone();
        saveTasks();
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The index of the task to be unmarked.
     * @throws AngelException If the task does not exist or if there is an error while saving tasks.
     */
    public void unmarkTask(int index) throws AngelException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException("Task not found: " + (index + 1));
        }
        tasks.get(index).unmark();
        saveTasks();
    }

    /**
     * Deletes a task from the list and saves the updated list.
     *
     * @param index The index of the task to be deleted.
     * @throws AngelException If the task does not exist or if there is an error while saving tasks.
     */
    public void deleteTask(int index) throws AngelException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException("Task not found: " + (index + 1));
        }
        tasks.remove(index);
        saveTasks();
    }

    /**
     * Lists all tasks in the current task list.
     */
    public ArrayList<Task> listTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Saves the current task list to the storage file.
     *
     * @throws AngelException If there is an error while saving tasks.
     */
    private void saveTasks() throws AngelException {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new AngelException("Error saving tasks: " + e.getMessage());
        }
    }
}
