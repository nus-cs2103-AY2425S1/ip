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
    private static Random random = new Random();

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
            processInput(input);
            System.out.println("------------------------------------");
        }
        System.out.println("------------------------------------");
        System.out.println("Goodbye! Remember, an apple a day keeps the doctor away, but I hope to see you sooner!");
        System.out.println("------------------------------------");
        
        scanner.close();
    }

    private static void processInput(String input) {
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
                System.out.println("I'm not sure what you mean. Please try again.");
                break;
        }
    }

    private static void handleMarkUnmark(String command, String[] parts) {
        if (parts.length > 1) {
            try {
                int index = Integer.parseInt(parts[1]) - 1;
                taskList.markTask(index, command.equals("mark"));
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid task number.");
            }
        } else {
            System.out.println("Please specify a task number to " + command + ".");
        }
    }

    private static void handleTodo(String[] parts) {
        if (parts.length > 1) {
            taskList.addTask(new Todo(parts[1]));
        } else {
            System.out.println("The description of a todo cannot be empty.");
        }
    }

    private static void handleDeadline(String[] parts) {
        if (parts.length > 1) {
            String[] deadlineParts = parts[1].split(" /by ");
            if (deadlineParts.length == 2) {
                taskList.addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
            } else {
                System.out.println("Please provide a deadline in the format: deadline <description> /by <deadline>");
            }
        } else {
            System.out.println("The description of a deadline cannot be empty.");
        }
    }

    private static void handleEvent(String[] parts) {
        if (parts.length > 1) {
            String[] eventParts = parts[1].split(" /from | /to ");
            if (eventParts.length == 3) {
                taskList.addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
            } else {
                System.out.println("Please provide an event in the format: event <description> /from <start> /to <end>");
            }
        } else {
            System.out.println("The description of an event cannot be empty.");
        }
    }
}