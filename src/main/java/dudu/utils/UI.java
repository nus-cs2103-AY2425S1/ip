package dudu.utils;

import java.util.ArrayList;
import java.util.Scanner;

import dudu.task.Task;

/**
 * Represents the user interaction portion of the chatbot
 */
public class UI {
    private static String welcomeMessage = LineWrapper.wrap("Hello! I'm dudu\n"
            + "What can I do for you?");
    private static String goodbyeMessage = LineWrapper.wrap("Bye. Hope to see you again soon!");

    private Scanner sc;

    /**
     * Creates a scanner instance
     */
    public UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a welcome message
     */
    public void welcomeMessage() {
        System.out.println(UI.welcomeMessage);
    }

    /**
     * Prints a goodbye message
     */
    public String goodbyeMessage() {
        this.sc.close();
        return goodbyeMessage;
    }

    /**
     * Prints a help message
     */
    public String helpMessage() {
        return "Please use help to get the list of commands";
    }

    /**
     * Prints an error message
     *
     * @param e The exception which contains the message to be printed
     */
    public String showError(Exception e) {
        return LineWrapper.wrap(e.toString());
    }

    /**
     * Prints a message to notify user of successful task addition
     *
     * @param task The task to be added
     * @param size The total number of tasks after adding the task
     */
    public String addTask(Task task, int size) {
        String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n"
                + "    %s\nNow you have %d tasks in the list.", task, size));
        return output;
    }

    /**
     * Prints a message to notify user of successful task being mark as completed
     *
     * @param task The task to be marked as completed
     */
    public String markTask(Task task) {
        return LineWrapper.wrap(String.format("Nice! I've marked this task as done:\n    %s", task));
    }

    /**
     * Prints a message to notify user of successful task being mark as uncompleted
     *
     * @param task The task to be marked as uncompleted
     */
    public String unmarkTask(Task task) {
        return LineWrapper.wrap(String.format("OK, I've marked this task as not done yet:\n    %s", task));
    }

    /**
     * Prints a message to notify user of successful task being deleted
     *
     * @param task The task to be deleted
     */
    public String deleteTask(Task task) {
        return LineWrapper.wrap(String.format("Noted. I've removed this task:\n    %s", task));
    }

    /**
     * Gets user input
     *
     * @return The user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the current list of tasks
     *
     * @param tasklist The taskList instance containing the list of tasks to be printed
     */
    public String printTasks(TaskList tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        if (tasks.isEmpty()) {
            return LineWrapper.wrap("No tasks");
        } else {
            StringBuilder output = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                output.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            return LineWrapper.wrap(output.toString());
        }
    }

    /**
     * Prints a message to notify user of tasks that match the query
     *
     * @param tasks The tasks that match the query
     */
    public String findTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return LineWrapper.wrap("No matching tasks in your list");
        } else {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                output.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            return LineWrapper.wrap(output.toString());
        }
    }
}
