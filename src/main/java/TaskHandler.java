import java.util.ArrayList;

/**
 * Handles the list of tasks, as well as all operations concerning tasks.
 * This class does not interface with input or output.
 */
public class TaskHandler {
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the `Task` at the specified position in this list.
     *
     * @param index index of the desired task. index is in [0, n-1].
     * @return The task at the position.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Gets the list of tasks as a formatted string, with each task on a separate line.
     *
     * @return the list of tasks: 1. ___ \n 2. ___ \n ...
     */
    public String getTaskListString() {
        StringBuilder taskListString = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            String itemized = String.format("%d. %s\n", i+1, taskList.get(i));
            taskListString.append(itemized);
        }

        return taskListString.toString().trim();
    }
}
