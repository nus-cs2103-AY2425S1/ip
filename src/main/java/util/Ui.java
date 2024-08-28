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
        System.out.println("    Hello! I'm Prince");
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
        showLine();
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        System.out.println("No tasks were found in storage!");
    }

    /**
     * Closes the scanner.
     */
    public void showBye() {
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

}
