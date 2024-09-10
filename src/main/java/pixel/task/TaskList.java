package pixel.task;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks. It provides methods to add,
 * delete, and retrieve tasks from the list.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with the same tasks as the given TaskList.
     *
     * @param taskList the TaskList to copy tasks from
     */
    public TaskList(TaskList taskList) {
        this.list = new ArrayList<Task>();
        for (int i = 0; i < taskList.list.size(); i++) {
            Task originalTask = taskList.list.get(i);
            this.list.add(originalTask);
        }
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTaskAtIndex(int index) {
        return list.get(index);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param task the task to delete
     */
    public void deleteTask(Task task) {
        list.remove(task);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the number of tasks in the TaskList
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns a string representation of the TaskList.
     *
     * @param keyword the keyword to search for
     * @return a string representation of the TaskList
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.toString().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
}
