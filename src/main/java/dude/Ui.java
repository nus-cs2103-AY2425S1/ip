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
     *
     * @return The String message for greeting.
     */
    public String showGreet() {
        return "Hey! I'm " + BOT_NAME
                + "\nWhat do you want?"
                + "\nCATCHPHRASE!!!";
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
     * @return A String representation of the list of tasks.
     */
    public String showList(TaskList taskList) {
        int size = taskList.getLength();

        if (size == 0) {
            return "Don't you have anything to do? Time to add some tasks!";
        }

        StringBuilder s = new StringBuilder();
        s.append("Here's your task list! Get going and tackle those tasks right away:\n");

        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 1; i <= size; i++) {
            s.append(i + "." + tasks.get(i - 1));

            if (i < size) {
                s.append('\n');
            }
        }

        return s.toString();
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A String message indicating that the task has been marked as done.
     */
    public String showMark(Task task) {
        return "Finally you have done something! I've marked this as done:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return A String message indicating that the task has been marked as not done.
     */
    public String showUnmark(Task task) {
        return "OK, Looks like you have more works to do. I've marked this as not done yet:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been added to the TaskList.
     *
     * @param task The task that has been added.
     * @param taskList The TaskList containing the newly added task.
     * @return A String message confirming the task adding and showing the updated task count.
     */
    public String showAdd(Task task, TaskList taskList) {
        return "Got it. Task added:\n" + task
                + "\nNow you have " + taskList.getLength() + " tasks in the list.";
    }

    /**
     * Returns a message indicating that a task has been removed from the TaskList.
     *
     * @param task The task that has been removed.
     * @param taskList The TaskList after the task has been removed.
     * @return A String message confirming the task removal and showing the updated task count.
     */
    public String showDelete(Task task, TaskList taskList) {
        return "All right! Task eliminated:\n" + task
                + "\nNow you have " + taskList.getLength() + " tasks in the list.";
    }

    /**
     * Returns the filtered list of tasks that match the description.
     *
     * @param filteredList The filtered list of tasks to display.
     * @return A String representation of the filtered list of tasks.
     */
    public String showFind(ArrayList<Task> filteredList) {
        int size = filteredList.size();
        StringBuilder s = new StringBuilder();
        s.append("Here's what you looking for:\n");

        for (int i = 1; i <= size; i++) {
            s.append(i + "." + filteredList.get(i - 1));

            if (i < size) {
                s.append('\n');
            }
        }

        return s.toString();
    }

    /**
     * Returns a message indicating that a new shortcut has been defined.
     *
     * @param shortcut The shortcut that has been defined.
     * @param command The CommandType that the shortcut mapped to.
     * @return A String message indicating that a new shortcut has been defined.
     */
    public String showDefine(String shortcut, CommandType command) {
        return "Did you just said that \"" + shortcut + "\" means \"" + command.name() + "\"?";
    }

    /**
     * Returns a message indicating that a shortcut has been deleted.
     *
     * @param shortcut The shortcut that is being deleted.
     * @return A String message indicating that a shortcut has been deleted.
     */
    public String showUndefine(String shortcut) {
        return "I got you. What does \"" + shortcut + "\" even means?";
    }

    /**
     * Returns an error message when a DudeException occurs.
     *
     * @param e The DudeException that occurred.
     * @return A String representation of the DudeException occurs.
     */
    public String showError(DudeException e) {
        return e.getMessage();
    }

    /**
     * Returns a goodbye message.
     *
     * @return A String message for farewell.
     */
    public String showBye() {
        return "Playtime's over!!\n"
                + "Better not bother me next time!";
    }

    /**
     * Displays a message in Text UI.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Closes the Scanner used for user input.
     */
    public void closeScanner() {
        scanner.close();
    }
}
