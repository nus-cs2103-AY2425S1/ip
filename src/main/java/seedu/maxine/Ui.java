package seedu.maxine;

import java.util.ArrayList;

import seedu.maxine.task.Task;
/**
 * The Ui class handles user interactions and displays messages to the console.
 * It provides methods for greeting the user, displaying task information,
 * and reporting errors or search results.
 */
public class Ui implements MaxineUi {
    /**
     * Constructs new instance of Ui class
     */
    public Ui() {
        // nothing
    }

    /**
     * Prints a greeting message to the console.
     * <p>
     * This method outputs a friendly greeting message to the console, introducing
     * the application or object as "Maxine" and expressing a pleasant welcome to the user.
     * </p>
     * */
    public String greet() {
        return "Hi! Nice to meet you :) I am Maxine";
    }

    /**
     * Prints a farewell message to the console.
     */
    public String goodbye() {
        return "\nBye! I have been maxed out and am going to sleep. "
                + "Hope to see you again soon!";
    }

    /**
     * Returns a message indicating that the specified task is being deleted.
     *
     * @param task The task to be deleted.
     * @return A string message confirming the deletion of the task.
     */
    public String delete(Task task) {
        return "Deleting this task: " + task;
    }

    /**
     * Returns a message indicating that the specified task has been marked as done.
     *
     * @param task The task that has been completed.
     * @return A string message confirming that the task has been marked.
     */
    public String mark(Task task) {
        return "Yay! You finally did something today: " + task;
    }
    /**
     * Returns a message indicating that the specified task has been unmarked as done.
     *
     * @param task The task that has been undone.
     * @return A string message confirming that the task has been unmarked.
     */
    public String unmark(Task task) {
        return "Undoing... this task? :'( :" + task;
    }
    /**
     * Displays all tasks in the given task list.
     * <p>
     * This method prints each task in the list, preceded by its index (starting from 1).
     * The index is used to help users identify and refer to tasks in the list.
     * Each task is printed on a new line with its corresponding index.
     * </p>
     *
     * @param list The {@code MaxineList} containing the tasks to be displayed.
     *             Each task in the list is printed with its index and details.
     */
    public String showList(MaxineList list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(list.get(i))
                    .append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays an error message indicating that loading data failed.
     * <p>
     * This method prints a predefined message to the console to notify the user
     * that there was an issue with loading data, represented by the message
     * "I...can't...load...X_X".
     * </p>
     */
    public String showLoadingError() {
        return "I...can't...load...X_X";
    }

    /**
     * Displays an error message to the console.
     * <p>
     * This method prints the provided error message to the console. It allows for
     * flexible error reporting by taking a string message as a parameter and
     * outputting it directly.
     * </p>
     *
     * @param e The error message to be printed. Should be a descriptive string
     *          explaining the error encountered.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * Performs a search for tasks matching the provided search term and displays the results.
     * <p>
     * This method queries the storage for tasks that contain the given search term, then
     * prints out the results with each matching task indexed.
     * </p>
     *
     * @param tasks The search term to use for querying tasks. Only tasks that contain this
     *               term in their description will be included in the results.
     */
    public String search(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        sb.append("Here are the results to your search:\n");
        int count = 1;
        for (Task task : tasks) {
            sb.append(count)
                    .append(". ")
                    .append(task.toString())
                    .append("\n");
            count++;
        }

        return sb.toString();
    }
    public String deleteAll() {
        return "All tasks have been deleted!";
    }
}
