package seedu.maxine.stubs;

import seedu.maxine.task.MaxineList;
import seedu.maxine.ui.MaxineUi;
import seedu.maxine.task.Task;

import java.util.ArrayList;

public class UiStub implements MaxineUi {
    /**
     * Constructs new instance of Ui class
     */
    public UiStub() {
        // nothing
    }

    /**
     * Prints a greeting message to the console.
     * <p>
     * This method outputs a friendly greeting message to the console, introducing
     * the application or object as "Maxine" and expressing a pleasant welcome to the user.
     * </p>
     *
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
     * Prints a message indicating that a task is being deleted.
     * <p>
     * This method outputs a message to the console that includes the task to be
     * deleted. It helps in tracking the task deletion process by providing
     * feedback on which task is being removed.
     * </p>
     *
     * @param task The task that is being deleted. This parameter should not be null.
     *             The task's string representation will be included in the output message.
     *
     * @throws NullPointerException if {@code task} is {@code null}.
     *
     */
    public String delete(Task task) {
        return "Deleting this task: " + task;
    }

    /**
     * Updates and displays the status of the specified task.
     * <p>
     * If the task is marked as completed (i.e., its status is true), 
     * this method prints a congratulatory message along with the task details.
     * If the task is not completed (i.e., its status is false), 
     * it prints a different message along with the task details.
     * </p>
     *
     * @param task The task whose status is to be updated and displayed. 
     *             The task's status is used to determine which message to print.
     */
    public String mark(Task task) {
        return "Yay! You finally did something today: " + task;
    }
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
     * @param list The {@code TaskList} containing the tasks to be displayed.
     *             Each task in the list is printed with its index and details.
     */
    public String showList(MaxineList list) {
        return "1. [T][ ] test command class";
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
     * prints out the results with each matching task indexed. The search results are 
     * prefixed with "Here are the results to your search:" to indicate the start of the 
     * search results.
     * </p>
     *
     * @param search The search term to use for querying tasks. Only tasks that contain this
     *               term in their description will be included in the results.
     */
    public String search(ArrayList<Task> search) {
        return "Here are the results to your search:\n"
                + "[T][ ] test command class";
    }
    public String deleteAll() {
        return "All tasks have been deleted!";
    }
}
