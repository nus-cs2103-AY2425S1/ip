package samson;

import samson.task.Task;
import samson.task.TaskList;

import java.util.List;
import java.util.Scanner;

/**
 * The <code> Ui </code> class handles all interactions with the user.
 * It manages input from the user and output to the console,
 * including displaying messages and task information.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new <code> Ui </code> object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void welcomeMessage() {
        showLine();
        System.out.println(" Hello! I'm Samson");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message when the chatbot ends.
     */
    public void goodbyeMessage() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        // System.out.println("____________________________________________________________");
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command entered by the user as a <code> String </code>.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line, used to separate different sections of the output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        showLine();
        System.out.println(" OOPS!!! " + message);
    }

    /**
     * Displays an error message when there is a failure in loading tasks from the file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        showLine();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.get(i));
        }
        showLine();
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param taskList The updated list of tasks.
     */
    public void showTaskAdded(Task task, TaskList taskList) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @param taskList The updated list of tasks.
     */
    public void showTaskDeleted(Task task, TaskList taskList) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Displays a message indicating that a task has been unmarked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Displays an error message when the task number provided by the user is invalid.
     */
    public void showTaskNumInvalid() {
        showLine();
        System.out.println(" Task number out of range.");
        showLine();
    }

    /**
     * Displays the results of a find operation.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     */
    public void showFindResults(List<Task> matchingTasks) {
        showLine();
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
        showLine();
    }
}
