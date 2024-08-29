package monique.tasklist;

import java.util.ArrayList;

import monique.task.Task;

/**
 * The <code>TaskList</code> class manages a list of tasks in the Monique application.
 * It provides operations to add, delete, mark, and unmark tasks, as well as retrieve
 * tasks and the total number of tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new <code>TaskList</code> object with the specified list of tasks.
     *
     * @param taskList An <code>ArrayList</code> of tasks to initialize the task list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param taskNum The index of the task to be deleted (0-based).
     */
    public void deleteTask(int taskNum) {
        this.taskList.remove(taskNum);
    }

    /**
     * Marks a task as completed based on its index.
     *
     * @param taskNum The index of the task to be marked as completed (0-based).
     */
    public void markTask(int taskNum) {
        this.taskList.set(taskNum, taskList.get(taskNum).mark());
    }

    /**
     * Unmarks a task as incomplete based on its index.
     *
     * @param taskNum The index of the task to be unmarked as incomplete (0-based).
     */
    public void unmarkTask(int taskNum) {
        this.taskList.set(taskNum, taskList.get(taskNum).unmark());
    }

    /**
     * Retrieves the entire task list.
     *
     * @return An <code>ArrayList</code> of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Retrieves a task from the task list based on its index.
     *
     * @param i The index of the task to retrieve (0-based).
     * @return The task at the specified index.
     */
    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    /**
     * Retrieves the total number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumItems() {
        return this.taskList.size();
    }
}
