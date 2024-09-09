package lama;

import java.util.ArrayList;

import lama.task.Task;

/**
 * Represents a list of tasks.
 * Provides methods to manage tasks such as adding, removing and retrieving tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Construct an empty Task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param taskList ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        assert taskList != null : "Task list should not be null";
        this.taskList = taskList;
    }

    /**
     * Return the number of tasks in the list.
     *
     * @return The size of task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified index in the list.
     *
     * @param i Integer index of the task in the task list.
     * @return Task at the specified index.
     */
    public Task get(int i) {
        assert i >= 0 && i < taskList.size() : "Index out of bounds";
        return taskList.get(i);
    }

    /**
     * Add task to the task list.
     *
     * @param task Task to be added into the task list.
     */
    public void add(Task task) {
        assert task != null : "Task to be added should not be null";
        taskList.add(task);
    }

    /**
     * Remove the task at the specified index in the task list.
     *
     * @param i Integer index of the task in the task list.
     * @return Task that was removed from the task list.
     */
    public Task remove(int i) {
        assert i >= 0 && i < taskList.size() : "Index out of bounds";
        return taskList.remove(i);
    }

    /**
     * Finds and returns a list of tasks that contain the specified word.
     *
     * @param word The keyword to search for in the task description.
     * @return TaskList contain tasks that matched the word given.
     */
    public TaskList find(String word) {
        assert word != null && !word.isBlank() : "Search keyword should not be null or empty";

        TaskList tasks = new TaskList();

        for (Task task: taskList) {
            if (task.toString().toLowerCase().contains(word.toLowerCase())) {
                tasks.add(task);
            }
        }

        return tasks;
    }


}
