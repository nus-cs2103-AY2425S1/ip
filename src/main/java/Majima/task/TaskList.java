package Majima.task;

import Majima.MajimaException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds the task provided in the argument to the TaskList.
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task of the corresponding index in the TaskList object.
     * Handles invalid indexes (out of range)
     *
     * @param index The int value of the corresponding task
     * */
    public Task deleteTask(int index) throws MajimaException {
        if (index < 0 || index >= tasks.size()) {
            throw new MajimaException("Kiryu! That number ain't even there!");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves all tasks. Used when getting all tasks in order to write to the
     * .txt file by the Storage class.
     *
     * @return A List<Task> containing all tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return a Boolean value corresponding to whether the TaskList is empty or not.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the size/number of tasks in the TaskList.
     *
     * @return an integer corresponding to the size.
     */
    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Finds tasks which descriptions contain the keyword supplied
     *
     * @param keyword The keyword to look for
     * @return A list of tasks containing the keyword
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}

