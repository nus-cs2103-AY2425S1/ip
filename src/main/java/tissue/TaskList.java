package tissue;

import java.util.ArrayList;

public class TaskList {
    private final String INDENT = "       ";
    private ArrayList<Task> taskArray = new ArrayList<>();

    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    /**
     * Returns task at the index.
     *
     * @param index The index of the task.
     * @return Task at the index.
     */
    public Task retrieveTask(int index) {
        return taskArray.get(index);
    }

    /**
     * Returns number of tasks.
     *
     * @return Number of tasks.
     */
    public int size() {
        return taskArray.size();
    }

    /**
     * Adds specified task to the list.
     *
     * @param task The task to add to the list.
     */
    public void add(Task task) {
        taskArray.add(task);
    }

    @Override
    public String toString() {
        String parsedText = "";
        for (int i = 0; i < taskArray.size(); i++) {
            Task task = taskArray.get(i);
            parsedText += INDENT + (i + 1) + "." + " " + task + "\n";
        }
        return parsedText;
    }

    /**
     * Deletes specified task from the list.
     *
     * @param number The task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int number) {
        return taskArray.remove(number - 1);
    }
}
