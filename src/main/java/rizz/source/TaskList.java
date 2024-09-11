package rizz.source;
import java.util.ArrayList;
import rizz.task.Task;


/**
 * Represents a list of tasks.
 * This class provides methods to manage tasks such as adding, deleting, and exporting tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param index The 1-based index of the task to be removed.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The 1-based index of the task to retrieve.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Exports all tasks in the task list to an array of strings.
     * Each task is formatted for file storage by calling its export() method.
     *
     * @return An array of strings where each string represents a task in the export format.
     */
    public String[] export() {
        String[] exportTasks = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            exportTasks[i] = tasks.get(i).export();
        }
        return exportTasks;
    }

    public TaskList findByKeyword(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (int i=0; i<tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getText().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
    
}