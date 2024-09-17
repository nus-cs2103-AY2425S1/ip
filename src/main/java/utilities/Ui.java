package utilities;

import tasks.Task;

/**
 * The Ui class handles interaction with the user by displaying messages and task information.
 */
public class Ui {

    /**
     * Constructs a Ui object to handle user interactions.
     */
    public Ui() {

    }

    /**
     * Displays a welcome message when the chatbot is initialized.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bigmouth\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a generic line separator for better readability.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a goodbye message when the chatbot is exited.
     */
    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that has been added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task The task that has been removed.
     * @param taskCount The current number of tasks remaining in the list.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }
}
