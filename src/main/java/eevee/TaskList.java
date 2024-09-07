package eevee;

import java.util.ArrayList;

/**
 * Represents a TaskList object that stores the user's tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the TaskList.
     *
     * @return The TaskList object.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a specific task given its task number.
     *
     * @param taskNumber The index of the desired task.
     * @return The requested Task object.
     * @throws EeveeException If the given task number is invalid.
     */
    public Task getTask(int taskNumber) throws EeveeException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new EeveeException("No task under the given task number. "
                    + "Did you type the wrong number?");
        }
        return tasks.get(taskNumber - 1);
    }

    /**
     * Adds a Task object to the TaskList given the Task.
     *
     * @param t The Task object to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a Task from the TaskList given its task number.
     *
     * @param taskNumber The task number of the Task to be removed.
     * @throws EeveeException If the given task number is invalid.
     */
    public void removeTask(int taskNumber) throws EeveeException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new EeveeException("No task under the given task number. "
                    + "Did you type the wrong number?");
        }
        tasks.remove(taskNumber - 1);
    }

    /**
     * Returns the size (number of elements) of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds tasks that have descriptions matching the given keyword.
     *
     * @param keyword The String that is to be found in task descriptions.
     * @return The ArrayList of tasks that have matching descriptions.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Prints out the tasks in the TaskList.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
           return "No tasks yet! Start adding tasks :)";
        }

        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        tasks.forEach((task) -> sb.append(tasks.indexOf(task) + 1).append(". ").append(task).append("\n"));
        return sb.toString();
    }
}
