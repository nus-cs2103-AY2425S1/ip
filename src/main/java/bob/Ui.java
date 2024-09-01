package bob;

import java.util.ArrayList;

/**
 * Represents the user interface of the Bob chatbot.
 * The Ui class handles all user interactions, displaying messages and formatting output.
 * It provides methods to show welcome and goodbye messages, display tasks, handle errors,
 * and show results based on user input.
 */
public class Ui {

    /**
     * Displays the welcome message when the bot starts.
     *
     * @param botName The name of the chatbot.
     */
    public void showWelcomeMessage(String botName) {
        System.out.println("____________________________________________________________");
        System.out.printf("Hello! I'm %s!\n", botName);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the goodbye message when the bot ends.
     */
    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a horizontal line for formatting output in the UI.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is added to the list.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Displays a message when a task is deleted from the list.
     *
     * @param deleted The string representation of the deleted task.
     * @param size The new size of the task list.
     */
    public void showTaskDeleted(String deleted, int size) {
        System.out.println(deleted);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(" Error: " + errorMessage);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param mark The string representation of the marked task.
     */
    public void showTaskMarked(String mark) {
        System.out.println(mark);
    }

    /**
     * Displays a message when a task is unmarked as done.
     *
     * @param unmark The string representation of the unmarked task.
     */
    public void showTaskUnmarked(String unmark) {
        System.out.println(unmark);
    }

    /**
     * Displays a list of tasks that match the search key.
     *
     * @param tasksWithKey An ArrayList of tasks that contain the keyword in their descriptions.
     */
    public void showTasksFound(ArrayList<Task> tasksWithKey) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasksWithKey.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasksWithKey.get(i));
        }
    }
}
