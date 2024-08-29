package hoshi.task;



import java.util.ArrayList;

/**
 * TaskList is a arrayList with several methods specific to handling tasks for Hoshi
 *
 */
public class TaskList {

    /**
     * Initialize a task list using ArrayList as type
     *
     */
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks the initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Constructs a TaskList with an empty ArrayList.
     *
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }


    /**
     * Adds a task to the task list
     *
     * @param task the task to be added to task list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Gets a task corresponding to the provided index from the task list
     *
     * @param index the index of the task to retrieve
     * @return the task corresponding to the index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes a task from the task list
     *
     * @param index the task to be added to taskList.
     */
    public void delete(int index) {

        taskList.remove(index);
    }

    /**
     * Marks a task from the task list
     *
     * @param index the index of the task to be marked
     */
    public void setMark(int index) {
        taskList.get(index).setIsDone(true);
    }

    /**
     * Returns the size of the task list
     *
     * @return the size of the task list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns a string representation of all tasks in the task list
     *
     * @return a string containing all tasks and corresponding indices
     */
    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
        }

        return result.toString();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
