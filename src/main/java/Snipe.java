import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The {@code Snipe} class represents a task management system that interacts with the user via command-line input.
 * It supports commands for adding, deleting, marking, unmarking, and listing tasks.
 */
public class Snipe {

    /** The name of the application. */
    private static final String NAME = "Snipe";

    /** The ASCII art logo for the application. */
    private static final String LOGO
            = "  _________      .__               \n"
            + " /   _____/ ____ |__|_____   ____  \n"
            + " \\_____  \\ /    \\|  \\____ \\_/ __ \\ \n"
            + " /        \\   |  \\  |  |_> >  ___/ \n"
            + "/_______  /___|  /__|   __/ \\___  >\n"
            + "        \\/     \\/   |__|        \\/ \n";

    /** A horizontal line used for formatting output. */
    private static final String HORIZONTAL_LINE = "_".repeat(60);

    /** The list of tasks managed by the application. */
    private static ArrayList<Task> list = new ArrayList<Task>();

    /**
     * Enumeration of command types that can be executed by the application.
     */
    public enum CommandType {
        LIST,
        HELP,
        DELETE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        BYE
    }

    /**
     * Initializes the chat, greeting the user and handling user input.
     */
    public void initChat() {
        greetUser();
        handleUserInput();
    }

    /**
     * Greets the user with an opening message and the application name.
     */
    private void greetUser() {
        String OPENING_MESSAGE = "Hello! I'm\n" + NAME +"\nWhat can I do for you?";
        printWithLines(OPENING_MESSAGE);
    }

    /**
     * Determines the {@link CommandType} based on the user input.
     *
     * @param userInput The input provided by the user.
     * @return The corresponding {@link CommandType}, or {@code null} if the command is not recognized.
     */
    private CommandType getCommandType(String userInput) {
        String[] split = userInput.split(" ");
        if (checkForHelp(split)) {
            return CommandType.HELP;
        }
        String command = split[0].toUpperCase();  // Extract the command part and convert to uppercase
        try {
            return CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            return null;  // Return null if the command is not recognized
        }
    }

    /**
     * Checks if the user's input includes a request for help.
     *
     * @param split The split array of user input.
     * @return {@code true} if the input contains "HELP", otherwise {@code false}.
     */
    private boolean checkForHelp(String[] split) {
        for (String str: split) {
            if (str.equalsIgnoreCase("HELP")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Continuously handles user input, executing commands until the user exits.
     */
    private void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            CommandType commandType = getCommandType(userInput);
            if (commandType != null) {
                if (commandType == CommandType.BYE) {
                    exitChat(scanner);
                    break;
                }
                handleSpecialInputs(commandType, userInput);
            } else {
                printWithLines("Invalid command. Type 'help' for a list of commands.");
            }
        }
    }

    /**
     * Handles the execution of special commands based on the {@link CommandType}.
     *
     * @param commandType The type of command to be executed.
     * @param userInput   The input provided by the user.
     */
    private void handleSpecialInputs(CommandType commandType, String userInput) {
        try {
            switch (commandType) {
                case LIST:
                    returnList();
                    break;
                case HELP:
                    displayHelp();
                    break;
                case DELETE:
                    deleteTask(userInput);
                    break;
                case MARK:
                case UNMARK:
                    markAndUnmarkTask(commandType, userInput);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task newTask = addTask(commandType, userInput);
                    list.add(newTask);
                    printWithLines(" Got it. I've added this task:\n  " + newTask + listLength());
                    break;
                default:
                    throw new SnipeException("Unknown command. Type 'help' for a list of commands.");
            }
        } catch (SnipeException e) {
            printWithLines(e.getMessage());
        }
    }

    /**
     * Returns a string indicating the number of tasks in the list.
     *
     * @return A string showing the number of tasks currently in the list.
     */
    private String listLength() {
        return String.format("\n Now you have %d %s in the list.", list.size(), list.size() == 1 ? "task" : "tasks");
    }

    /**
     * Displays help instructions by reading from a file.
     *
     * @throws SnipeException if the file cannot be read.
     */
    private void displayHelp() throws SnipeException{
        String filePath = "src/main/txt/helpinstructions.txt"; // Instructions manual
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            printWithLines(content); // Print the instructions
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * Deletes a task from the list based on user input.
     *
     * @param userInput The input provided by the user, including the task number to delete.
     * @throws SnipeException if the task number is invalid or the list item does not exist.
     */
    private void deleteTask(String userInput) throws SnipeException{
        String[] split = userInput.split(" ", 2);
        if (split.length < 2 || split[1].trim().isEmpty()) {
            throw new SnipeException("Please input a number. Use 'help for correct syntax");
        }
        int index = Integer.valueOf(split[1]) - 1;
        if (index > list.size() - 1) {
            throw new SnipeException("This list item does not exist!\n"
                    + listLength());
        } else {
            Task toRemove = list.get(index);
            list.remove(index);
            String message = "Noted. I've removed this task:\n"
                    + toRemove.toString()
                    + listLength();
            printWithLines(message);
        }
    }

    /**
     * Marks or unmarks a task as completed or not completed based on user input.
     *
     * @param commandType The command type, either MARK or UNMARK.
     * @param userInput   The input provided by the user, including the task number.
     * @throws SnipeException if the task number is invalid or the list item does not exist.
     */
    private void markAndUnmarkTask(CommandType commandType, String userInput) throws SnipeException {
        String [] split = userInput.split(" ", 2);
        if (split.length < 2 || split[1].trim().isEmpty()) {
            throw new SnipeException("Please input a number. Use 'help for correct syntax");
        }
        int index = Integer.valueOf(split[1]) - 1;
        if (index > list.size() - 1) {
            throw new SnipeException("This list item does not exist!\n"
                    + listLength());
        } else {
            Task task = list.get(index);
            boolean successful = task.getStatus() ^ commandType == CommandType.MARK;
            if (successful) {
                task.changeStatus();
                String markAndNotDone = "Nice! I've marked this task as done:\n" +
                        task.toString();
                String unmarkAndDone = "OK, I've marked this task as not done yet:\n" +
                        task.toString();
                printWithLines(commandType == CommandType.MARK ? markAndNotDone : unmarkAndDone);
            } else {
                String markAndDone = "This task is already marked done!";
                String unmarkAndNotDone = "This task is currently not done yet!";
                printWithLines(commandType == CommandType.MARK ? markAndDone : unmarkAndNotDone);
            }
        }
    }

    /**
     * Adds a new task to the list based on the command type and user input.
     *
     * @param commandType The command type (TODO, DEADLINE, or EVENT).
     * @param userInput   The input provided by the user, including the task details.
     * @return The created {@link Task} object.
     * @throws SnipeException If the input is invalid or incomplete.
     */
    private Task addTask(CommandType commandType, String userInput) throws SnipeException {
        String[] split = userInput.split(" ", 2);
        if (split.length < 2 || split[1].trim().isEmpty()) {
            throw new SnipeException("The description of a task cannot be empty. Use 'help' for correct syntax.");
        }

        String taskDescription = split[1];
        switch (commandType) {
            case TODO:
                return new ToDo(taskDescription);
            case DEADLINE:
                String[] deadlineSplit = taskDescription.split(" /by ", 2);
                if (deadlineSplit.length < 2) {
                    throw new SnipeException("A deadline requires a description and a /by date.");
                }
                return new Deadline(deadlineSplit[0], deadlineSplit[1]);
            case EVENT:
                String[] eventSplit = taskDescription.split(" /from | /to ");
                if (eventSplit.length < 3) {
                    throw new SnipeException("An event requires a description, a /from date, and a /to date.");
                }
                return new Event(eventSplit[0], eventSplit[1], eventSplit[2]);
            default:
                throw new SnipeException("Invalid task type.");
        }
    }

    /**
     * Displays the list of tasks currently managed by the application.
     */
    private void returnList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            String item = String.format("%d. ", i + 1) + list.get(i).toString();
            System.out.println(item);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message between two horizontal lines for better readability.
     *
     * @param message The message to be printed.
     */
    private void printWithLines(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Exits the chat and closes the scanner.
     *
     * @param scanner The {@link Scanner} object used for input.
     */
    private void exitChat(Scanner scanner) {
        String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
        printWithLines(CLOSING_MESSAGE);
        scanner.close();
    }

    /**
     * The main method that starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Snipe snipe = new Snipe();
        snipe.initChat();
    }
}


