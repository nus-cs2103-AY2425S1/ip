package james;

import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the specified list of tasks.
     * @param tasks List of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList by its index.
     * @param index The index of the task to be removed.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList by its index.
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks a task as completed by its index.
     * @param index The index of the task to be marked.
     */
    public void markTask(int index) {
        tasks.get(index).mark();
    }

    /**
     * Marks a task as not completed by its index.
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unMark();
    }

    /**
     * Builds a formatted string representing all tasks in the TaskList.
     * Each task is displayed on a new line, prefixed by its index number.
     *
     * @return A string containing all tasks in the TaskList, each prefixed
     *         by its index and followed by a newline character.
     */
    public String printTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append((i + 1) + ". " + tasks.get(i).printTask() + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Builds a formatted string of all tasks that contain the specified keyword in their description.
     *
     * This method iterates through all tasks in the task list and includes those
     * whose description contains the provided substring. Each matching task is
     * listed with a sequential number starting from 1.
     *
     * @param substring The substring to search for within task descriptions.
     * @return A string containing all tasks that match the specified keyword,
     *         each prefixed by a sequential number and followed by a newline character.
     */
    public String printMatchingTasks(String substring) {
        int count = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).matchSubstring(substring)) {
                stringBuilder.append(count + ". " + tasks.get(i).printTask() + "\n");
                count++;
            }
        }
        return stringBuilder.toString();
    }

}
