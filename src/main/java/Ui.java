import java.util.ArrayList;

import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 */
public class Ui {

    private final Scanner scannerInput;
    static ArrayList<Task> taskList = new ArrayList<Task>();

    private final static String LOGO = " _____      _                   __   __          \n"
            + "|  ___|   _| |_ _   _ _ __ ___  \\ \\ / /__  _   _ \n"
            + "| |_ | | | | __| | | | '__/ _ \\  \\ V / _ \\| | | |\n"
            + "|  _|| |_| | |_| |_| | | |  __/   | | (_) | |_| |\n"
            + "|_|   \\__,_|\\__|\\__,_|_|  \\___|   |_|\\___/ \\__,_|\n";

    /**
     * Constructs a Ui instance with a new Scanner.
     */
    public Ui() {
        this.scannerInput = new Scanner(System.in);
    }
    /**
     * Shows a message to the user.
     *
     * @param message The message to print.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Greets the user with a welcome message.
     */
    public void hello() {
        System.out.println("____________________________________________________________\n" +
                "Yo! It's \n" + LOGO + "\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

    }

    /**
     * Bids the user farewell with a message.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        this.scannerInput.close();
    }

    /**
     * Prints the tasks in the user's tasklist.
     */
    public static void listTasks() {
        int count = 1;
        System.out.println("Items in List:");
        for (Task item : taskList) {
            System.out.println(count++ + ". " + item.print());
        }
    }
/**
     * Replies to the user's command.
     *
     * @param userCommand The command inputted by the user.
     */
    public void respond(String userCommand) {
    try {
        
    } catch (Exception e) {
    }
    }
}
