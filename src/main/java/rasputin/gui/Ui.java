package rasputin.gui;

import java.util.Scanner;

import rasputin.task.Task;
import rasputin.task.TaskList;

/**
 * Represents the UI that the user will interact with when using the chatbot.
 */
public class Ui {

    private static final String LINE_BREAK = "____________________________________";
    private static Scanner scanner = new Scanner(System.in);


    /**
     * Prints the message after a command is executed in between two line breaks.
     *
     * @param text Text to be printed.
     */
    public static void printText(String text) {
        System.out.println(LINE_BREAK);
        System.out.println(text);
        System.out.println(LINE_BREAK + "\n");
    }

    /**
     * Prints the list of tasks in between two line breaks.
     *
     * @param tasks List of tasks to be printed.
     */
    public static String printList(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Prints the successful addition of a task into the TaskList.
     *
     * @param task Task to be added into the list.
     * @param tasks List of tasks to be used.
     */
    public static String printAddTask(Task task, TaskList tasks) {
        String output = String.format("Added %s task:\n%s", task.getType(), task);
        output += "\nYou currently have " + tasks.size() + "rasputin/s in your list.";
        return output;
    }

    /**
     * Prints the greeting in between two line breaks when the user starts the chatbot.
     */
    public static String printGreeting() {
        return "Hello, I'm Rasputin!\nWhat can I do for you?";
    }

    /**
     * Prints the farewell in between two line breaks when the user is done with the chatbot.
     */
    public static String printFarewell() {
        return "Bye. See you later!";
    }

    /**
     * Prints the error message in between two line breaks.
     *
     * @param error Error message to be printed.
     */
    public static String printError(String error) {
        return error;
    }

    /**
     * Reads the user input from the terminal.
     *
     * @return User input to be passed into the parser.
     */
    public static String readInput() {
        return scanner.nextLine().trim();
    }
}

