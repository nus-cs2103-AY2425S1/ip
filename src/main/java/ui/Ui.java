package ui;

import tasks.Task;

import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user. It manages input and output, displaying messages, and reading user commands.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and logo when the application starts.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Jar\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a line separator in the console.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the goodbye message when the application ends.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the user's command from the console.
     *
     * @return The user's command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a response message in the console.
     *
     * @param response The message to be displayed.
     */
    public void showResponse(String response) {
        System.out.println(response);
    }

    /**
     * Displays the list of tasks in the console.
     *
     * @param taskList The string representation of the task list.
     */
    public void showTaskList(String taskList) {
        System.out.println("Here are the tasks in your list:\n" + taskList);
    }

    /**
     * Displays a message indicating that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Displays a message indicating that a task has been marked as not completed.
     *
     * @param task The task that was marked as not completed.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param taskContent The string representation of the task that was added.
     */
    public void showTaskAdded(String taskContent) {
        System.out.println("Added: " + taskContent);
    }

    /**
     * Displays the current count of tasks in the list.
     *
     * @param count The number of tasks in the list.
     */
    public void showTaskCount(int count) {
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     */
    public void showDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }
}
