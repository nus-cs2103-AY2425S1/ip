package FRIDAY;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * User interface of the app
 */
public class Ui {
    private static final String GREETING_MESSAGE = "Hello! I'm FRIDAY\nWhat can I do for you?\n";
    private static final String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String DIVIDER = "----------------------------------------\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greeting() {
        System.out.println(DIVIDER + GREETING_MESSAGE + DIVIDER);
    }

    public void farewell() {
        System.out.println(DIVIDER + FAREWELL_MESSAGE + DIVIDER);
    }

    /**
     * Prints out message upon adding of task to the task list
     * @param task Task object
     * @param numTasks  number of tasks in list
     */
    public void printAdd(Task task, int numTasks) {
        System.out.println(DIVIDER + "Got it. I've added this taskL:\n"
                + task.getTaskDescription() + "\nNow you have " + numTasks + " tasks in your list\n" + DIVIDER);
    }

    /**
     * Prints out message on deletion of task from task list
     * @param task Task object
     * @param numTasks size of task list
     */
    public void printRemove(Task task, int numTasks) {
        System.out.println(DIVIDER + "Noted. I've removed this taskL:\n"
                + task.getTaskDescription() + "\nNow you have " + numTasks + " tasks in your list\n" + DIVIDER);
    }

    public void printCheck() {
        System.out.println(DIVIDER + "Noted. I've marked this task as complete\n" + DIVIDER);
    }

    public void printUncheck() {
        System.out.println(DIVIDER + "Noted. I've marked this task as incomplete\n" + DIVIDER);
    }
    public String readUserInput() {
        return this.scanner.nextLine();
    }

    public boolean isActive() {
        return this.scanner.hasNextLine();
    }

    public void display(String input) {
        System.out.println(input);
    }

    /**
     * method displays the list of tasks in a specific format
     * @param searchResults array list of tasks
     */
    public void displaySearchResults(ArrayList<Task> searchResults) {
        StringBuilder output = new StringBuilder(DIVIDER + "Here are the matching tasks from your list\n:");
        searchResults.forEach((task) -> {
            output.append(task);
        });
        output.append("\n" + DIVIDER);
        System.out.println(output);
    }
}
