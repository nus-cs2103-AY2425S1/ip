package demurebot.ui;

import demurebot.task.Task;
import demurebot.task.TaskList;

/**
 * The Ui class handles all user interface interactions for the DemureBot application.
 * It provides methods to display various messages and task lists to the user.
 */
public class Ui {
    /**
     * Displays the end message when the user exits the application.
     * @return The end message.
     */
    public String displayEnd() {
        return "Bye~~ Hope to see you again soon!";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return The message indicating that a task has been marked as done.
     */
    public String displayMarkTask(Task task) {
        return ("Nice! You have completed\n  "
                + task + "\n"
                + "Very demure, very mindful!\n"
            );
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return The message indicating that a task has been marked as not done.
     */
    public String displayUnmarkTask(Task task) {
        return ("Oh no! You haven't completed\n  "
                + task + "\n"
                + "Not very demure, not very mindful!\n"
            );
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that has been deleted.
     * @param size The current number of tasks in the list.
     * @return The message indicating that a task has been deleted.
     */
    public String displayDeleteTask(Task task, int size) {
        return ("Ok dear, I've removed this task:\n  "
                + task + "\n"
                + " Now you have " + size + " tasks in the list.\n"
            );
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that has been added.
     * @param size The current number of tasks in the list.
     * @return The message indicating that a task has been added.
     */
    public String displayAddTask(Task task, int size) {
        return ("Of course, I've added this task:\n  "
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n"
            );
    }

    /**
     * Displays a message indicating that the task list is empty.
     * @return The message indicating that the task list is empty.
     */
    public String displayEmptyList() {
        return "There are no tasks:DDD\n";
    }

    /**
     * Displays a message indicating that the search failed.
     * @return The message indicating that the search failed.
     */
    public String displayNoTasksFound() {
        return "No tasks matching your description were found;-;";
    }
    /**
     * Displays the list of tasks.
     *
     * @param list The TaskList containing the tasks to be displayed.
     * @return The list of tasks.
     */
    public String displayList(TaskList list) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < list.getSize(); i++) {
            Task task = list.getTask(i);
            response.append((i + 1)).append(".").append(task).append("\n");
        }
        return response.toString();
    }
}
