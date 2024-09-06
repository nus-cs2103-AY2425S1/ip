package alfred.task;

import java.util.ArrayList;
import java.util.List;

import alfred.ui.AlfredResponse;

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
     * Returns the deleted task so that a message can be generated.
     *
     * @param taskNumber The position of the task to be deleted (1-based index).
     * @return The deleted task object.
     */
    public Task deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);

        // Return the deleted task so it can be used in the response
        return task;
    }

    /**
     * Updates the completion status of the task at the specified position in the list.
     * The task number is 1-based, so taskNumber - 1 is used to access the correct index.
     * Returns a message indicating whether the task was marked as done or undone.
     *
     * @param taskNumber The position of the task to be updated (1-based index).
     * @param mark <code>true</code> to mark the task as done, <code>false</code> to unmark it.
     * @return A string message indicating the task's updated status.
     */
    public String updateTaskStatus(int taskNumber, boolean mark) {
        Task task = tasks.get(taskNumber - 1);
        if (mark) {
            task.markAsDone();
            return AlfredResponse.showTaskMarked(task); // Return the marked task message
        } else {
            task.unmark();
            return AlfredResponse.showTaskUnmarked(task); // Return the unmarked task message
        }
    }

    /**
     * Marks the task at the specified position in the list as done.
     * The task number is 1-based, so taskNumber - 1 is used to access the correct index.
     * Returns the task that was marked as done.
     *
     * @param taskNumber The position of the task to be marked (1-based index).
     * @return The Task that was marked as done.
     */
    public Task markTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks the task at the specified position in the list (marks it as not done).
     * The task number is 1-based, so taskNumber - 1 is used to access the correct index.
     * Returns the task that was unmarked.
     *
     * @param taskNumber The position of the task to be unmarked (1-based index).
     * @return The Task that was unmarked (marked as not done).
     */
    public Task unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.unmark();
        return task;
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
