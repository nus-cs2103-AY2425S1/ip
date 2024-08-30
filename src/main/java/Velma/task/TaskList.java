package Velma.task;


import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for Velma.task.TaskList if tasks are provided.
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for Velma.task.TaskList.
     * Creates an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets a task from the list.
     * @param index
     * @return Velma.task.Task from the specified list index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the size of the list.
     * @return size
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Deletes a task from the list.
     * @param index
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets all tasks in the list.
     * @return all tasks in list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks with the specified keyword.
     */

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
