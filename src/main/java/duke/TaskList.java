package duke;

import java.util.ArrayList;

/**
 * The duke.TaskList class manages a list of tasks. It provides methods to add, delete, and display tasks,
 * as well as to retrieve the list of tasks.
 */
public class TaskList {

    private ArrayList<IndividualTask> tasks = new ArrayList<IndividualTask>();



    private String message = "";
    private String indent = "      ";
    private String separator = "------------------------------";


    /**
     * Constructs a duke.TaskList with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     * @throws MentalHealthException If there is an issue initializing the task list.
     */

    public TaskList(ArrayList<IndividualTask> tasks) throws MentalHealthException {
        this.tasks = tasks;

    }

    /**
     * Constructs an empty duke.TaskList.
     */
    public TaskList() {

    }

    /**
     * Displays all tasks in the list with their corresponding numbers.
     * The tasks are printed with indentation and separated by a visual separator.
     */
    public String getTasks(ArrayList<IndividualTask> tasks) {
        StringBuilder result = new StringBuilder();
        result.append(this.indent).append(this.separator).append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            String number = String.valueOf(i + 1);
            String format = this.formatListMessage(number, tasks.get(i));
            result.append(format).append("\n");
        }
        result.append(this.indent).append(this.separator);

        return result.toString();
    }


    /**
     * Returns the list of tasks managed by this duke.TaskList.
     *
     * @return The list of tasks.
     */
    public ArrayList<IndividualTask> getListTask() {
        return this.tasks;
    }

    /**
     * Deletes the task at the specified position in the list.
     *
     * @param num The position of the task to be deleted (0-based index).
     */
    public void deleteTask(int num) {
        this.tasks.remove(num);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(IndividualTask task) {
        this.tasks.add(task);
    }

    /**
     * Formats a task message for display, including the task's number and description.
     *
     * @param number The number of the task in the list.
     * @param task The task to be formatted.
     * @return The formatted task message.
     */
    public String formatListMessage(String number, IndividualTask task) {
        return this.indent + number + "." + task.toString();
    }
}
