package gavinchatbot.util;

import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import java.util.Scanner;

/**
 * Handles the user interface of the GavinChatBot application.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Gavin's Chat Bot!\n");
        System.out.println("What can I do for you?\n");
        System.out.println(horizontalLine);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks.
     * @throws GavinException If there is an error while retrieving the tasks.
     */
    public void showList(TaskList tasks) throws GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        System.out.println(horizontalLine);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @throws GavinException If there is an error while marking the task.
     */
    public void showMarkedTask(Task task) throws GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
        System.out.println(horizontalLine);

    }

    /**
     * Displays a message indicating that a task has been unmarked (marked as not done).
     *
     * @param task The task that was unmarked.
     * @throws GavinException If there is an error while unmarking the task.
     */
    public void showUnmarkedTask(Task task) throws GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task);
        System.out.println(horizontalLine);

    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param size The current size of the task list.
     * @throws GavinException If there is an error while adding the task.
     */
    public void showAddedTask(Task task, int size) throws GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @param size The current size of the task list.
     */
    public void showDeletedTask(Task task, int size) {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("!!!ERROR!!! " + message);
        System.out.println(horizontalLine);
    }

    /**
     * Displays an error message when there is an issue loading tasks from a file.
     */
    public void showLoadingError() {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
        System.out.println(horizontalLine);
    }

    /**
     * Reads and returns a command input by the user.
     *
     * @return The command input by the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
