package futureyou;

import java.util.Scanner;

import futureyou.exception.FutureYouException;

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
    public String bye() {
        String msg = ("Bye. Hope to see you again soon!" + System.lineSeparator()
                + "____________________________________________________________\n");
        displayMessage(msg);
        return msg;
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
    public String hello() {
        StringBuilder message = new StringBuilder();
        message.append("____________________________________________________________")
                .append(System.lineSeparator())
                .append("Hi this is Future You here")
                .append(System.lineSeparator())
                .append("I have the following commands available:")
                .append(System.lineSeparator())
                .append("____________________________________________________________")
                .append(System.lineSeparator())
                .append("1) todo/deadline/event")
                .append(System.lineSeparator())
                .append("2) mark <task number>")
                .append(System.lineSeparator())
                .append("3) delete <task number>")
                .append(System.lineSeparator())
                .append("4) list")
                .append(System.lineSeparator())
                .append("5) find <task name>")
                .append(System.lineSeparator())
                .append("6) sort")
                .append(System.lineSeparator())
                .append("7) bye")
                .append(System.lineSeparator());
        displayMessage(message.toString());
        return message.toString();
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
    public String respond(String userCommand) {
        assert (userCommand != null);
        String message = "";
        try {
            String command = Parser.parseCommand(userCommand);
            switch (command) {
            case "todo": {
                message = Parser.parseToDoTask(userCommand);
                break;
            }
            case "list":
                message = TaskList.listTasks();
                break;
            case "mark": {
                int taskNum = Parser.parseTaskNumber(userCommand);
                message = TaskList.markTask(taskNum);
                break;
            }
            case "delete": {
                int taskNum = Parser.parseTaskNumber(userCommand);
                message = TaskList.deleteTask(taskNum);
                break;
            }
            case "bye":
                message = bye();
                close();
                break;
            case "deadline": {
                message = Parser.parseDeadlineTask(userCommand);
                break;
            }
            case "event": {
                message = Parser.parseEventTask(userCommand);
                break;
            }
            case "find": {
                message = TaskList.findTask(userCommand.substring(4));
                break;
            }
            case "sort": {
                message = TaskList.sortTask();
                break;
            }
            default: // User enters invalid command
                message = "Please enter a valid command!";
                break;
            }
            displayMessage(message);
            return message;
        } catch (FutureYouException e) {
            message = e.getMessage();
            displayMessage(message);
            return message;
        } catch (Exception e) {
            message = "Error Accorded when parsing command";
            displayMessage(message);
            return message;
        }
    }
}
