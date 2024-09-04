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
    public void goodbyeMessage() {
        System.out.println(goodbyeMessage);
        this.sc.close();
    }

    /**
     * Prints a help message
     */
    public void helpMessage() {
        System.out.println("Please use help to get the list of commands");
    }

    /**
     * Prints an error message
     *
     * @param e The exception which contains the message to be printed
     */
    public void showError(Exception e) {
        System.out.println(LineWrapper.wrap(e.toString()));
    }

    /**
     * Prints a message to notify user of successful task addition
     *
     * @param task The task to be added
     * @param size The total number of tasks after adding the task
     */
    public void addTask(Task task, int size) {
        String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n"
                + "    %s\nNow you have %d tasks in the list.", task, size));
        System.out.println(output);
    }

    /**
     * Prints a message to notify user of successful task being mark as completed
     *
     * @param task The task to be marked as completed
     */
    public void markTask(Task task) {
        String output = LineWrapper.wrap(String.format("Nice! I've marked this task as done:\n    %s", task));
        System.out.println(output);
    }

    /**
     * Prints a message to notify user of successful task being mark as uncompleted
     *
     * @param task The task to be marked as uncompleted
     */
    public void unmarkTask(Task task) {
        String output = LineWrapper.wrap(String.format("OK, I've marked this task as not done yet:\n    %s", task));
        System.out.println(output);
    }

    /**
     * Prints a message to notify user of successful task being deleted
     *
     * @param task The task to be deleted
     */
    public void deleteTask(Task task) {
        String output = LineWrapper.wrap(String.format("Noted. I've removed this task:\n    %s", task));
        System.out.println(output);
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
    public void printTasks(TaskList tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        if (tasks.isEmpty()) {
            System.out.println(LineWrapper.wrap("No tasks"));
        } else {
            StringBuilder output = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                output.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(LineWrapper.wrap(output.toString()));
        }
    }

    /**
     * Prints a message to notify user of tasks that match the query
     *
     * @param tasks The tasks that match the query
     */
    public void findTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(LineWrapper.wrap("No matching tasks in your list"));
        } else {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                output.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(LineWrapper.wrap(output.toString()));
        }
    }
}
