package Johnson.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * This class provides methods to add, retrieve, complete tasks, uncomplete tasks, delete tasks,
 * and get the
 * task count.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();

    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add.
     * @return true if the task was added successfully, false otherwise.
     */
    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified index as complete.
     *
     * @param index the index of the task to mark as complete.
     * @return true if the task was marked as complete, false otherwise.
     */
    public boolean markTask(int index) {
        return tasks.get(index).completeTask();
    }

    /**
     * Marks the task at the specified index as incomplete.
     *
     * @param index the index of the task to mark as incomplete.
     * @return true if the task was marked as incomplete, false otherwise.
     */
    public boolean unmarkTask(int index) {
        return tasks.get(index).uncompleteTask();
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to delete.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int taskCount() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res = res + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return res;
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword the keyword to search for.
     * @return a list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskName().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
