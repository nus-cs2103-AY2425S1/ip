package dude;

import java.util.ArrayList;
import java.util.Scanner;

import dude.exception.DudeException;
import dude.task.Task;
import dude.task.TaskList;

/**
 * Represents the user interface for interacting with the user.
 * This class handles input from the user and displays messages.
 */
public class Ui {
    private static final String BOT_NAME = "Dude";
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a Ui and initializes the Scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the greeting message.
     */
    public String showGreet() {
        return "Hello! I'm " + BOT_NAME + "\nWhat can I do for you?\n";
    }

    /**
     * Reads and returns the user's input as a string.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns the list of tasks currently in the TaskList.
     *
     * @param taskList The TaskList with list of tasks to display.
     */
    public String showList(TaskList taskList) {
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks in your list:\n");

        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 1; i <= taskList.getLength(); i++) {
            s.append(i + "." + tasks.get(i - 1) + '\n');
        }

        return s.toString();
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public String showMark(Task task) {
        return "Nice! I've marked this task as done:\n" + task + '\n';
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public String showUnmark(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task + '\n';
    }

    /**
     * Returns a message indicating that a task has been added to the TaskList.
     *
     * @param task The task that has been added.
     * @param taskList The TaskList containing the newly added task.
     */
    public String showAdd(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + taskList.getLength() + " tasks in the list.\n";
    }

    /**
     * Returns a message indicating that a task has been removed from the TaskList.
     *
     * @param task The task that has been removed.
     * @param taskList The TaskList after the task has been removed.
     */
    public String showDelete(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + taskList.getLength() + " tasks in the list.\n";
    }

    /**
     * Returns the filtered list of tasks that match the description.
     *
     * @param filteredList The filtered list of tasks to display.
     */
    public String showFind(ArrayList<Task> filteredList) {
        StringBuilder s = new StringBuilder();
        s.append("Here are the matching tasks in your list:\n");

        for (int i = 1; i <= filteredList.size(); i++) {
            s.append(i + "." + filteredList.get(i - 1) + '\n');
        }

        return s.toString();
    }

    public String showDefine(String shortcut, CommandType command) {
        return "Noted. Now I know that \"" + shortcut + "\" represents \"" + command.name() + "\".\n";
    }

    public String showUndefine(String shortcut) {
        return "I got you. What does \"" + shortcut + "\" means?\n";
    }

    /**
     * Returns an error message when a DudeException occurs.
     *
     * @param e The DudeException that occurred.
     */
    public String showError(DudeException e) {
        return e.getMessage();
    }

    /**
     * Returns a goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays a message in Text UI.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(LINE);
        System.out.print(message);
        System.out.println(LINE);
    }

    /**
     * Closes the Scanner used for user input.
     */
    public void closeScanner() {
        scanner.close();
    }
}
