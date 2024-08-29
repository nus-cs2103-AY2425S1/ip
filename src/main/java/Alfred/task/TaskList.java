package alfred.task;

import alfred.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks. Provides methods for adding, deleting, and updating tasks.
 * Also provides access to the current list of tasks and their count.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty <code>TaskList</code>.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a <code>TaskList</code> with the specified initial tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the list of tasks.
     *
     * @return A list of <code>Task</code> objects.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getTasksCount() {
        return tasks.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The <code>Task</code> to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified position in the list.
     * The task number is 1-based, so taskNumber - 1 is used to access the correct index.
     * Displays a message indicating the task has been deleted and the remaining task count.
     *
     * @param taskNumber The position of the task to be deleted (1-based index).
     */
    public void deleteTask(int taskNumber) {
        Task task = tasks.remove(taskNumber - 1);
        Ui.showTaskDeleted(task, tasks.size());
    }

    /**
     * Updates the completion status of the task at the specified position in the list.
     * The task number is 1-based, so taskNumber - 1 is used to access the correct index.
     * Displays a message indicating whether the task was marked as done or undone.
     *
     * @param taskNumber The position of the task to be updated (1-based index).
     * @param mark <code>true</code> to mark the task as done, <code>false</code> to unmark it.
     */
    public void updateTaskStatus(int taskNumber, boolean mark) {
        Task task = tasks.get(taskNumber - 1);
        if (mark) {
            task.markAsDone();
            Ui.showTaskMarked(task);
        } else {
            task.unmark();
            Ui.showTaskUnmarked(task);
        }
    }

    /**
     * Finds and returns tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
