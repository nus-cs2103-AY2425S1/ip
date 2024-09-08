package futureyou;

import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 */
public class Ui {

    private static final String LOGO = " _____      _                   __   __          " + System.lineSeparator()
            + "|  ___|   _| |_ _   _ _ __ ___  \\ \\ / /__  _   _ " + System.lineSeparator()
            + "| |_ | | | | __| | | | '__/ _ \\  \\ V / _ \\| | | |" + System.lineSeparator()
            + "|  _|| |_| | |_| |_| | | |  __/   | | (_) | |_| |" + System.lineSeparator()
            + "|_|   \\__,_|\\__|\\__,_|_|  \\___|   |_|\\___/ \\__,_|" + System.lineSeparator();
    private final Scanner scannerInput;

    /**
     * Constructs a Ui instance with a new Scanner.
     */
    public Ui() {
        this.scannerInput = new Scanner(System.in);
    }

    /**
     * Bids the user farewell with a message.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!" + System.lineSeparator()
                + "____________________________________________________________\n");
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
        System.out.println("____________________________________________________________" + System.lineSeparator()
                + "Yo! It's" + System.lineSeparator() + LOGO
                + "What can I do for you?" + System.lineSeparator()
                + "____________________________________________________________\n");

    }

    /**
     * Reads the user's command.
     *
     * @return The command entered by the user.
     */
    public String readUserCommand() {
        return scannerInput.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        this.scannerInput.close();
    }

    /**
     * Replies to the user's command.
     *
     * @param userCommand The command inputted by the user.
     */
    public void respond(String userCommand) {
        try {
            String command = Parser.parseCommand(userCommand);
            switch (command) {
            case "todo": {
                displayMessage(Parser.parseToDoTask(userCommand));
                break;
            }
            case "list":
                displayMessage(TaskList.listTasks());
                break;
            case "mark": {
                int taskNum = Parser.parseTaskNumber(userCommand);
                displayMessage(TaskList.markTask(taskNum));
                break;
            }
            case "delete": {
                int taskNum = Parser.parseTaskNumber(userCommand);
                displayMessage(TaskList.deleteTask(taskNum));
                break;
            }
            case "bye":
                bye();
                close();
                break;
            case "deadline": {
                displayMessage(Parser.parseDeadlineTask(userCommand));
                break;
            }
            case "event": {
                displayMessage(Parser.parseEventTask(userCommand));
                break;
            }
            default: // User enters invalid command
                displayMessage("Please enter a valid command!");
                break;
            }
        } catch (Exception e) {
            displayMessage("Please enter a valid command");
        }
    }
}
