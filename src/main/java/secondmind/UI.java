package secondmind;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interaction, including input and output operations.
 */
public class UI {
    private static final String line = "____________________________________________________________";

    private Scanner reader;

    /**
     * Constructor for the UI class.
     */
    public UI() {
        this.reader = new Scanner(System.in);
    }

    private void printLineSeparator() {
        System.out.println(line);
    }

    private void printErrorMessage(Exception e) {
        printLineSeparator();
        System.out.println(e);
        printLineSeparator();
    }

    /**
     * Prints a list of tasks to the console.
     *
     * @param taskList The list of tasks to print.
     * @param taskCount The number of tasks currently in the task list.
     */
    public void printTaskList(ArrayList<Task> taskList, int taskCount) {
        printLineSeparator();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(String.format("%d. %s", i+1, taskList.get(i)));
        }
        printLineSeparator();
    }

    /**
     * Outputs a message to the console.
     *
     * @param text The message to output.
     */
    public void output(String text) {
        printLineSeparator();
        System.out.println(text);
        printLineSeparator();
    }

    /**
     * Gets user input from the console.
     *
     * @return The input string from the user.
     */
    public String getInput() {
        if (reader.hasNext()) {
            String nextLine = reader.nextLine();
            return nextLine;
        }
        return "";
    }
}