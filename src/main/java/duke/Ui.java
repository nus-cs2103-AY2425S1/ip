package duke;

import java.util.ArrayList;

/**
 * Represents the user interface of the Duke application.
 * Handles interactions with the user, such as greeting, displaying tasks, and showing search results.
 */
public class Ui {

    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Prints a greeting message to the user.
     * This method is called when the application starts.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Prints a goodbye message to the user.
     * This method is called when the user exits the application.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param taskList the list of tasks to be printed
     */
    public void printList(TaskList taskList) {
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
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasksFound.size(); i++) {
            int serial = i + 1;
            Task task = tasksFound.get(i);
            System.out.println(serial + "." + task.toString());
        }
    }

}
