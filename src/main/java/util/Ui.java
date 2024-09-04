package util;

import java.util.Scanner;

import tasks.Task;
import tasks.TaskList;

/**
 * Ui deals with interactions with the user by displaying the relevant information.
 */
public class Ui {

    private static final String LINE = "    ___________________________________________";
    private Scanner sc;

    /**
     * A constructor for the Ui class.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays welcome message to user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm Prince :p");
        System.out.println("    What can I do for you?");
        showLine();
    }

    /**
     * Moves the scanner to the next line.
     * @return User input.
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Displays all tasks in a numbered list format.
     * @param taskList List of tasks.
     */
    public void showTaskList(TaskList taskList) {
        System.out.println("    Here are the tasks in your list:");
        int length = taskList.size();
        // print the list of inputs
        for (int i = 0; i < length; i++) {
            Task task = taskList.get(i);
            // formatting for numbering of list
            int listNum = i + 1;
            String numDot = listNum + ".";
            System.out.println("    " + numDot + task.toString());
        }
    }

    /**
     * Displays all tasks containing input keyword.
     * @param taskList List of tasks.
     */
    public void showMatchingTaskList(TaskList taskList) {
        System.out.println("    Here are the matching tasks in your list:");
        int length = taskList.size();
        // print the list of inputs
        for (int i = 0; i < length; i++) {
            Task task = taskList.get(i);
            // formatting for numbering of list
            int listNum = i + 1;
            String numDot = listNum + ".";
            System.out.println("    " + numDot + task.toString());
        }
    }

    public void showNoMatchingTasks() {
        System.out.println("    " + "There are no tasks that match your keyword!");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from storage!");
    }

    public void showAdd() {
        System.out.println("    Got it. I've added this task:");
    }

    public void showTaskToString(Task task) {
        System.out.println("      " + task.toString());
    }

    /**
     * Displays number of tasks for the user.
     * @param taskList List of tasks.
     */
    public void showNumberOfTasks(TaskList taskList) {
        System.out.println("    Now you have " + taskList.size()
                    + " tasks in the list.");
    }

    public void showDelete() {
        System.out.println("    Noted. I've removed this task:");
    }

    /**
     * Closes the scanner.
     */
    public void showBye() {
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

}
