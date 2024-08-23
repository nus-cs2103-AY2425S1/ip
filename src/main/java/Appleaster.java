import java.util.Scanner;
import java.util.Random;

public class Appleaster {
    private static final String LOGO = "   _____                .__                          __                \n"
                                     + "  /  _  \\ ______ ______ |  |   ____ _____    _______/  |_  ___________ \n"
                                     + " /  /_\\  \\\\____ \\\\____ \\|  | _/ __ \\\\__  \\  /  ___/\\   __\\/ __ \\_  __ \\\n"
                                     + "/    |    \\  |_> >  |_> >  |_\\  ___/ / __ \\_\\___ \\  |  | \\  ___/|  | \\/\n"
                                     + "\\____|__  /   __/|   __/|____/\\___  >____  /____  > |__|  \\___  >__|   \n"
                                     + "        \\/|__|   |__|             \\/     \\/     \\/            \\/       \n";

    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        System.out.println("------------------------------------");
        System.out.println(LOGO);
        System.out.println("Greetings, human! Ready for some apple-solutely amazing conversation?");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------");
        
        Scanner scanner = new Scanner(System.in);
        String input;
        
        while (true) {
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            
            System.out.println("------------------------------------");
            try {
                processInput(input);
            } catch (AppleasterException e) {
                System.out.println("Oops! " + e.getMessage());
                System.out.println("Please try again with a valid command.");
            }
            System.out.println("------------------------------------");
        }
        System.out.println("------------------------------------");
        System.out.println("Goodbye! Remember, an apple a day keeps the doctor away, but I hope to see you sooner!");
        System.out.println("------------------------------------");
        
        scanner.close();
    }

    private static void processInput(String input) throws AppleasterException {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "list":
                taskList.listTasks();
                break;
            case "mark":
            case "unmark":
                handleMarkUnmark(command, parts);
                break;
            case "todo":
                handleTodo(parts);
                break;
            case "deadline":
                handleDeadline(parts);
                break;
            case "event":
                handleEvent(parts);
                break;
            default:
                throw new AppleasterException("I don't recognize that command. Here are the commands I know: todo, deadline, event, list, mark, unmark.");
        }
    }

    private static void handleMarkUnmark(String command, String[] parts) throws AppleasterException {
        if (parts.length < 2) {
            throw new AppleasterException("Please provide a task number to " + command + ". For example: " + command + " 1");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            taskList.markTask(index, command.equals("mark"));
        } catch (NumberFormatException e) {
            throw new AppleasterException("The task number should be a valid integer. You provided: " + parts[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new AppleasterException("There is no task with the number " + parts[1] + ". Please check the task list and try again.");
        }
    }

    private static void handleTodo(String[] parts) throws AppleasterException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new AppleasterException("The description of a todo cannot be empty. Please provide a description after 'todo'.");
        }
        taskList.addTask(new Todo(parts[1]));
    }

    private static void handleDeadline(String[] parts) throws AppleasterException {
        if (parts.length < 2) {
            throw new AppleasterException("Please provide a deadline in the format: deadline <description> /by <deadline>");
        }
        String[] deadlineParts = parts[1].split(" /by ");
        if (deadlineParts.length != 2) {
            throw new AppleasterException("Invalid deadline format. Please use: deadline <description> /by <deadline>");
        }
        if (deadlineParts[0].trim().isEmpty()) {
            throw new AppleasterException("The description of a deadline cannot be empty.");
        }
        if (deadlineParts[1].trim().isEmpty()) {
            throw new AppleasterException("The deadline date/time cannot be empty.");
        }
        taskList.addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
    }

    private static void handleEvent(String[] parts) throws AppleasterException {
        if (parts.length < 2) {
            throw new AppleasterException("Please provide an event in the format: event <description> /from <start> /to <end>");
        }
        String[] eventParts = parts[1].split(" /from | /to ");
        if (eventParts.length != 3) {
            throw new AppleasterException("Invalid event format. Please use: event <description> /from <start> /to <end>");
        }
        if (eventParts[0].trim().isEmpty()) {
            throw new AppleasterException("The description of an event cannot be empty.");
        }
        if (eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
            throw new AppleasterException("The start and end times of an event cannot be empty.");
        }
        taskList.addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
    }
}