package velma.task;


import java.util.ArrayList;

/**
 * TaskList class - tasklist list that contains of different types of task
 */
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
     * Adds a task/multiple tasks to the list.
     * @param newTasks
     */
    public void addTask(Task... newTasks) {
        for (Task task : newTasks) {
            tasks.add(task);
        }
    }

    /**
     * Gets a task from the list.
     * @param index
     * @return Velma.task.Task from the specified list index.
     */
    public Task getTask(int index) {
        assert index >= 0 : "Index cannot be negative";
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
        assert index >= 0 : "Index cannot be negative";
        tasks.remove(index);
    }

    /**
     * Gets all tasks in the list.
     * @return all tasks in list
     */
    public ArrayList<Task> getTasks() {
        assert tasks != null : "Tasks cannot be null";
        return tasks;
    }

    /**
     * Finds tasks with the specified keyword.
     */

    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null : "Keyword cannot be null";
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
