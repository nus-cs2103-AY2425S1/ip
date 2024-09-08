package duke;

import java.util.ArrayList;

/**
 * Represents the user interface of the Duke application.
 * Handles interactions with the user, such as greeting, displaying tasks, and showing search results.
 */
public class Ui {

    private Duke duke;

    /**
     * Constructs an Ui instance with the specified Duke instance.
     *
     * @param duke The Duke instance used to manage application state and interactions.
     */
    public Ui(Duke duke) {
        assert duke != null : "Duke instance should not be null";

        this.duke = duke;
    }

    /**
     * Returns a greeting message from Duke.
     *
     * @return A string containing Duke's greeting message.
     */
    public String greet() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * Prints a goodbye message and sets Duke to offline mode.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        this.duke.goOffline();
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param taskList the list of tasks to be printed
     */
    public void printList(TaskList taskList) {
        assert taskList != null : "TaskList should not be null";

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            int serial = i + 1;
            Task task = taskList.getTask(i);
            System.out.println(serial + "." + task.toString());
        }
    }

    /**
     * Prints the list of tasks that match a search keyword.
     *
     * @param tasksFound The list of tasks that match the search criteria.
     */
    public void printKeywordList(ArrayList<Task> tasksFound) {
        assert tasksFound != null : "Tasks found list should not be null";

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasksFound.size(); i++) {
            int serial = i + 1;
            Task task = tasksFound.get(i);
            System.out.println(serial + "." + task.toString());
        }
    }

    /**
     * Prints the list of high-priority tasks from the given TaskList to the console.
     * A high-priority task is considered to be any task where the priority is non-zero.
     * If no high-priority tasks exist, no tasks are printed.
     *
     * @param taskList the TaskList containing tasks to be filtered by priority
     */
    public void printPriorityList(TaskList taskList) {
        assert taskList != null : "TaskList should not be null";
        int serial = 1;

        System.out.println("Here are the high priority tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getPriority() == 0) {
                continue;
            }
            System.out.println(serial + "." + task.toString());
            serial += 1;
        }
    }

}
