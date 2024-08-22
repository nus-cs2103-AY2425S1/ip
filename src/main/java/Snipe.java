import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Snipe {
    private static final String NAME = "Snipe";
    private static final String LOGO
            = "  _________      .__               \n"
            + " /   _____/ ____ |__|_____   ____  \n"
            + " \\_____  \\ /    \\|  \\____ \\_/ __ \\ \n"
            + " /        \\   |  \\  |  |_> >  ___/ \n"
            + "/_______  /___|  /__|   __/ \\___  >\n"
            + "        \\/     \\/   |__|        \\/ \n";
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private static ArrayList<Task> list = new ArrayList<Task>();
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
    public void initChat() {
        greetUser();
        handleUserInput();
    }

    private void greetUser() {
        String OPENING_MESSAGE = "Hello! I'm\n" + NAME +"\nWhat can I do for you?";
        printWithLines(OPENING_MESSAGE);
    }
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
    private boolean checkForHelp(String[] split) {
        for (String str: split) {
            if (str.equalsIgnoreCase("HELP")) {
                return true;
            }
        }
        return false;
    }
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
    private String listLength() {
        return String.format("\n Now you have %d %s in the list.", list.size(), list.size() == 1 ? "task" : "tasks");
    }
    private void displayHelp() throws SnipeException{
        String filePath = "src/main/txt/helpinstructions.txt"; // Instructions manual
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            printWithLines(content); // Print the instructions
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
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
    private void markAndUnmarkTask(CommandType commandtype, String userInput) throws SnipeException {
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
            boolean successful = task.getStatus() ^ commandtype == CommandType.MARK;
            if (successful) {
                task.changeStatus();
                String markAndNotDone = "Nice! I've marked this task as done:\n" +
                        task.toString();
                String unmarkAndDone = "OK, I've marked this task as not done yet:\n" +
                        task.toString();
                printWithLines(commandtype == CommandType.MARK ? markAndNotDone : unmarkAndDone);
            } else {
                String markAndDone = "This task is already marked done!";
                String unmarkAndNotDone = "This task is currently not done yet!";
                printWithLines(commandtype == CommandType.MARK ? markAndDone : unmarkAndNotDone);
            }
        }
    }
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
    private void returnList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            String item = String.format("%d. ", i + 1) + list.get(i).toString();
            System.out.println(item);
        }
        System.out.println(HORIZONTAL_LINE);
    }
    private void printWithLines(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }
    private void exitChat(Scanner scanner) {
        String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
        printWithLines(CLOSING_MESSAGE);
        scanner.close();
    }
    public static void main(String[] args) {
        Snipe snipe = new Snipe();
        snipe.initChat();
    }
}


