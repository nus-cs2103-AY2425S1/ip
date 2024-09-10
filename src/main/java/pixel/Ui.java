package pixel;

import java.util.Scanner;

import pixel.task.TaskList;

/**
 * The Ui class represents the user interface of the Pixel application. It
 * handles user input and output.
 */
public class Ui {
    private String name = "Pixel";
    private Scanner scanner;

    /**
     * Constructs a Ui object. Initializes the scanner to read user input from the
     * console.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The command entered by the user as a String.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        pixelSays("Hello! I'm " + name, "What can I do for you?\n");
    }

    /**
     * Prints a line separator to the console.
     */
    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints messages from Pixel to the console.
     *
     * @param args The messages to be printed.
     */
    public void pixelSays(String... args) {
        for (String arg : args) {
            System.out.println("    " + arg);
        }
    }

    /**
     * Returns a string of messages after taking input of messages from Pixel.
     *
     * @param args The messages to be printed.
     * @return The string of messages.
     */
    public String getPixelResponse(String... args) {
        String output = "";
        for (String arg : args) {
            output += "    " + arg + "\n";
        }
        return output;
    }

    /**
     * Displays the matching tasks to the user.
     *
     * @param matchingTasks The list of matching tasks.
     */
    public void showMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.size() == 0) {
            pixelSays("No matching tasks found!");
        } else {
            pixelSays("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                pixelSays((i + 1) + ". " + matchingTasks.getTaskAtIndex(i));
            }
        }
    }

    /**
     * Returns a string representation of the matching tasks.
     *
     * @param matchingTasks The list of matching tasks.
     * @return The string representation of the matching tasks.
     */
    public String getMatchingTasksResponse(TaskList matchingTasks) {
        if (matchingTasks.size() == 0) {
            return "No matching tasks found!";
        } else {
            String output = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                output += (i + 1) + ". " + matchingTasks.getTaskAtIndex(i) + "\n";
            }
            return output;
        }
    }

    /**
     * Closes the user interface by closing the scanner.
     */
    public void closeUi() {
        this.scanner.close();
    }
}
