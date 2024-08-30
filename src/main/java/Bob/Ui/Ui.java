package Bob.Ui;

import java.util.ArrayList;
import Bob.Tasks.Task;

/**
 * The Ui class of Bob chatbot is responsible for handling all user interactions.
 * This class provides methods to display messages, list of tasks, and handling of user
 * input and output.
 */

public class Ui {

    /**
     * Displays welcome message and logo when the chatbot starts.
     */
    public void showWelcome() {
        String logo = " ____        ____\n"
                + "| __ )  ___ | __ )\n"
                + "|  _ \\ / _ \\|  _ \\\n"
                + "| |_) | (_) | |_) |\n"
                + "|____/ \\___/|____/\n";
        printLine();
        System.out.println("Hello! I'm Bob!\nHow can I help you today?");
        System.out.println(logo);
        printLine();
    }

    /**
     * Displays goodbye messsage when the chatbot exits.
     */
    public void showGoodbye() {
        printLine();
        System.out.println("Bye, see you again :)");
        printLine();
    }

    /**
     * Displays error message when there is an error.
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Displays the current task list.
     * @param tasks an ArrayList of Task objects.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    /**
     * Displays the message signaling user that a task has been successfully added into the task list.
     * @param task The Task object that was added.
     * @param size The total number of tasks in the task list currently.
     */
    public void showAddedTask(Task task, int size) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " task(s) in the list.");
        printLine();
    }

    /**
     * Displays the message signaling user that a task has been successfully removed from the task list.
     * @param task The Task object that was removed.
     * @param size The total number of tasks in the task list currently.
     */
    public void showRemovedTask(Task task, int size) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " task(s) in the list.");
        printLine();
    }

    public void showTaskMarked(Task task) {
        printLine();
        System.out.println("Yay! I've marked this task as done:");
        System.out.println(task.toString());
        printLine();
    }

    public void showTaskUnmarked(Task task) {
        printLine();
        System.out.println("Alright, I've marked this task as not done yet:");
        System.out.println(task.toString());
        printLine();
    }

    public void showMatchingTasks(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    /**
     * Displays a horizontal line as a separator.
     */
    private void printLine() {
        System.out.println("------------------------------------------");
    }
}
