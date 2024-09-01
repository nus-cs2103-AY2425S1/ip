package fred;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles all user interactions, including printing messages
 * to the console and receiving user input. It is responsible for greeting the user,
 * printing task lists, and displaying messages in a consistent format.
 */
public class Ui {
    Scanner scanner;
    String line = "____________________________________________________________";

    /**
     * Constructs a new Ui object and initializes the scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a message to the user, surrounded by horizontal lines for formatting.
     *
     * @param message The message to be displayed to the user.
     */
    void say(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    /**
     * Greets the user with a welcome message when the application starts.
     */
    void greet() {
        String message = "Hello! I'm Fred\nWhat can I do for you?";
        say(message);
    }

    /**
     * Bids farewell to the user when the application is about to exit.
     */
    void sayFarewell() {
        String message = "Bye. Hope to see you again soon!";
        say(message);
    }

    /**
     * Prints the list of tasks provided by the user in a formatted manner.
     *
     * @param taskList The list of tasks to be printed.
     */
    void printTaskList(ArrayList<Task> taskList) {
        int index = 1;
        System.out.println(line);
        for (Task task : taskList) {
            System.out.println(String.format("%s.%s", index++, task));
        }
        System.out.println(line);
    }

    /**
     * Retrieves input from the user via the console.
     *
     * @return The input entered by the user as a String.
     */
    String getInput() {
        return scanner.nextLine();
    }
}
