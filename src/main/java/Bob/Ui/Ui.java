package bob.Ui;

import java.util.ArrayList;
import java.util.Scanner;

import bob.Tasks.Task;

/**
 * The Ui class of Bob chatbot is responsible for handling all user interactions.
 * This class provides methods to display messages, list of tasks, and handling of user
 * input and output.
 */

public class Ui {
    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);

    }
    /**
     * Displays welcome message and logo when the chatbot starts.
     */
    public String showWelcome() {
        String logo = " ____        ____\n"
                + "| __ )  ___ | __ )\n"
                + "|  _ \\ / _ \\|  _ \\\n"
                + "| |_) | (_) | |_) |\n"
                + "|____/ \\___/|____/\n";
        printLine();
        System.out.println("Bello! I'm Bob!\nHow can I help you today?");
        System.out.println(logo);
        printLine();

        // to support GUI
        return "Bello! I'm Bob!\nHow can I help you today?";
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays goodbye messsage when the chatbot exits.
     */
    public String showGoodbye() {
        printLine();
        System.out.println("Bye, see you again :)");
        printLine();
        return "Bye, see you again :)";
    }

    /**
     * Displays error message when there is an error.
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        printLine();
        System.out.println("Wait a minute..." + message);
        printLine();

        return "Wait a minute..." + message;
    }

    /**
     * Displays the current task list.
     * @param tasks an ArrayList of Task objects.
     */
    public String showTaskList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }

        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    /**
     * Displays the message signaling user that a task has been successfully added into the task list.
     * @param task The Task object that was added.
     * @param size The total number of tasks in the task list currently.
     */
    public String showAddedTask(Task task, int size) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " task(s) in the list.");
        printLine();
        printLine();

        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        response.append("    ").append(task).append("\n");
        response.append("Now you have ").append(size).append(" task(s) in the list.");
        return response.toString();
    }

    /**
     * Displays the message signaling user that a task has been successfully removed from the task list.
     * @param task The Task object that was removed.
     * @param size The total number of tasks in the task list currently.
     */
    public String showRemovedTask(Task task, int size) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " task(s) in the list.");
        printLine();

        StringBuilder response = new StringBuilder();
        response.append("Noted, I've removed this task from the list:\n");
        response.append("  ").append(task).append("\n");
        response.append("Now you have ").append(size).append(" task(s) in the list.");
        return response.toString();
    }

    public String showTaskMarked(Task task) {
        printLine();
        System.out.println("Yay! I've marked this task as done:");
        System.out.println(task.toString());
        printLine();

        StringBuilder response = new StringBuilder();
        response.append("Yay! I've marked this task as done:\n");
        response.append("    ").append(task);
        return response.toString();
    }

    public String showTaskUnmarked(Task task) {
        printLine();
        System.out.println("Alright, I've marked this task as not done yet:");
        System.out.println(task.toString());
        printLine();

        StringBuilder response = new StringBuilder();
        response.append("Alright, I've marked this task as not done yet:\n");
        response.append("    ").append(task);
        return response.toString();
    }

    public String showMatchingTasks(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();

        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i).toString());
        }
        return response.toString();
    }

    /**
     * Displays a horizontal line as a separator.
     */
    private void printLine() {
        // not shown in GUI
        System.out.println("------------------------------------------");
    }
}
