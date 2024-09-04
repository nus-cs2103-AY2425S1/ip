package pixel;

import java.util.Scanner;
import pixel.task.TaskList;

/**
 * The Ui class represents the user interface of the Pixel application.
 * It handles user input and output.
 */
public class Ui {
    private String name = "Pixel";
    private Scanner scanner;

    /**
     * Constructs a Ui object.
     * Initializes the scanner to read user input from the console.
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
        PixelSays("Hello! I'm " + name, "What can I do for you?\n");
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
    public void PixelSays(String... args) {
        for (String arg : args) {
            System.out.println("    " + arg);
        }
    }

    /**
     * Closes the user interface by closing the scanner.
     */
    public void showMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.size() == 0) {
            PixelSays("No matching tasks found!");
        } else {
            PixelSays("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                PixelSays((i + 1) + ". " + matchingTasks.getTaskAtIndex(i));
            }
        }
    }

    public void closeUi() {
        this.scanner.close();
    }
}
