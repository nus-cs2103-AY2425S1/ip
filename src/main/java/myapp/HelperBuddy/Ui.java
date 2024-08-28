package myapp.helperbuddy;

import java.util.Scanner;
import java.util.List;

public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object.
     * Initializes the Scanner object for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     * Shows a greeting and an invitation for the user to enter a command.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm YourHelperBuddy.");
        System.out.println("How may I assist you today?");
        System.out.println("________________________________________________");
    }

    /**
     * Displays a goodbye message to the user.
     * Shows a farewell message when the user exits the application.
     */
    public void showGoodbyeMessage() {
        System.out.println("________________________________________________");
        System.out.println("Goodbye. Take care and see you again!");
        System.out.println("________________________________________________");
    }

    /**
     * Displays the list of tasks to the user.
     * Shows all tasks in the provided TaskList with their corresponding index.
     * @param taskList The TaskList containing tasks to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        int index = 0;
        System.out.println("________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList.getTasks()) {
            System.out.println(++index + ". " + task);
        }
        System.out.println("________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been added.
     * Shows the details of the added task and the updated size of the task list.
     * @param task          The Task that was added to the list.
     * @param taskList_size The updated number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskList_size) {
        System.out.println("________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList_size + " tasks in the list.");
        System.out.println("________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been removed.
     * Shows the details of the removed task and the updated size of the task list.
     * @param task     The Task that was removed from the list.
     * @param taskList The TaskList containing the updated list of tasks.
     */
    public void showTaskRemoved(Task task, TaskList taskList) {
        System.out.println("________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     * Marks the provided task as done and shows its updated status.
     * @param task The Task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        try {
            System.out.println("________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            task.markDone();
            System.out.println("  " + task);
            System.out.println("________________________________________________");
        } catch (Exception e) {
            System.out.println("________________________________________________");
            System.out.println("Oops! There seems to be an issue with the task number you provided.");
            System.out.println("________________________________________________");
        }
    }

    /**
     * Displays a message indicating that a task has been marked as not done yet.
     * Marks the provided task as not done and shows its updated status.
     * @param task The Task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        try {
            System.out.println("________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            task.markUndone();
            System.out.println("  [" + task);
            System.out.println("________________________________________________");
        } catch (Exception e) {
            System.out.println("________________________________________________");
            System.out.println("Oops! There seems to be an issue with the task number you provided.");
            System.out.println("________________________________________________");
        }
    }

    /**
     * Filters out the tasks from the list which match the user keyword.
     * Displays all the relevant search results from the task list
     * @param tasks is the task list
     */
    public void showSearchResults(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("________________________________________________");
            System.out.println("No tasks found matching the search keyword.");
            System.out.println("________________________________________________");
        } else {
            int index = 0;
            System.out.println("________________________________________________");
            System.out.println("Search results:");
            for (Task task : tasks) {
                System.out.println(++index + ". " + task);
            }
            System.out.println("________________________________________________");
        }
    }

    /**
     * Reads user input from the console.
     * Prompts the user to enter a task or command and returns the input string.
     * @return The user's input as a String.
     */
    public String readUserInput() {
        System.out.println("Enter your task: ");
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }

    /**
     * Closes the Scanner object used for reading user input.
     * This method should be called when the application is closing to release resources.
     */
    public void close() {
        scanner.close();
    }
}