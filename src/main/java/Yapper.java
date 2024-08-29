import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Yapper {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/yapper.txt";

    public static void main(String[] args) {
        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);
        printWelcomeMessage();

        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] userInputParts = userInput.split(" ", 2);
                CommandType command = getCommandType(userInputParts[0]);

                switch (command) {
                    case BYE:
                        handleBye();
                        saveTasksToFile();
                        return;

                    case LIST:
                        handleList();
                        break;

                    case MARK:
                        handleMark(userInputParts);
                        saveTasksToFile();
                        break;

                    case UNMARK:
                        handleUnmark(userInputParts);
                        saveTasksToFile();
                        break;

                    case TODO:
                        handleTodo(userInputParts);
                        saveTasksToFile();
                        break;

                    case DEADLINE:
                        handleDeadline(userInputParts);
                        saveTasksToFile();
                        break;

                    case EVENT:
                        handleEvent(userInputParts);
                        saveTasksToFile();
                        break;

                    case DELETE:
                        handleDelete(userInputParts);
                        saveTasksToFile();
                        break;

                    default:
                        throw new UnknownCommandException();
                }
            } catch (YapperException e) {
                printErrorMessage(e.getMessage());
            }
        }
    }

    private static CommandType getCommandType(String command) {
        switch (command.toLowerCase()) {
            case "bye":
                return CommandType.BYE;
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "todo":
                return CommandType.TODO;
            case "deadline":
                return CommandType.DEADLINE;
            case "event":
                return CommandType.EVENT;
            case "delete":
                return CommandType.DELETE;
            default:
                return CommandType.UNKNOWN;
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Yapper");
        System.out.println(" What can I do for you Boss?");
        System.out.println("____________________________________________________________");
    }

    private static void printErrorMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    private static void handleBye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye Boss!. Yapper wants to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void handleList() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list Boss!:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private static void handleMark(String[] userInputParts) throws YapperException {
        if (userInputParts.length < 2) {
            throw new EmptyDescriptionException("mark");
        }
        int taskNumber = Integer.parseInt(userInputParts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new InvalidTaskNumberException(taskNumber);
        }
        tasks.get(taskNumber).markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice Boss! I've marked this task as done:");
        System.out.println("   " + tasks.get(taskNumber));
        System.out.println("____________________________________________________________");
    }

    private static void handleUnmark(String[] userInputParts) throws YapperException {
        if (userInputParts.length < 2) {
            throw new EmptyDescriptionException("unmark");
        }
        int taskNumber = Integer.parseInt(userInputParts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new InvalidTaskNumberException(taskNumber);
        }
        tasks.get(taskNumber).markAsNotDone();
        System.out.println("____________________________________________________________");
        System.out.println(" OK Boss! I've marked this task as not done yet:");
        System.out.println("   " + tasks.get(taskNumber));
        System.out.println("____________________________________________________________");
    }

    private static void handleTodo(String[] userInputParts) throws YapperException {
        if (userInputParts.length < 2) {
            throw new EmptyDescriptionException("todo");
        }
        Task task = new Todo(userInputParts[1]);
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it Boss! I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Okay Boss! Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleDeadline(String[] userInputParts) throws YapperException {
        if (userInputParts.length < 2) {
            throw new EmptyDescriptionException("deadline");
        }
        String[] details = userInputParts[1].split(" /by ");
        if (details.length < 2) {
            throw new YapperException("Please specify a deadline using the format: deadline [task] /by [date/time]");
        }
        Task task = new Deadline(details[0], details[1]);
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it Boss. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Okay Boss! Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleEvent(String[] userInputParts) throws YapperException {
        if (userInputParts.length < 2) {
            throw new EmptyDescriptionException("event");
        }
        String[] details = userInputParts[1].split(" /from ");
        if (details.length < 2) {
            throw new YapperException("Boss Please specify the event time using the format: event [task] /from [start time] /to [end time]");
        }
        String[] fromTo = details[1].split(" /to ");
        if (fromTo.length < 2) {
            throw new YapperException("Boss Please specify both the start and end time for the event dehhh");
        }
        Task task = new Event(details[0], fromTo[0], fromTo[1]);
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println(" Okay Boss! I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Okay look here Boss!" + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleDelete(String[] userInputParts) throws YapperException {
        if (userInputParts.length < 2) {
            throw new EmptyDescriptionException("delete");
        }
        int taskNumber = Integer.parseInt(userInputParts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new InvalidTaskNumberException(taskNumber);
        }
        Task removedTask = tasks.remove(taskNumber);
        System.out.println("____________________________________________________________");
        System.out.println(" Okay bOSS ur task got removed");
        System.out.println("   " + removedTask);
        System.out.println(" Okay Boss! Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    Task task = null;
                    switch (parts[0]) {
                        case "T":
                            task = new Todo(parts[2]);
                            break;
                        case "D":
                            task = new Deadline(parts[2], parts[3]);
                            break;
                        case "E":
                            task = new Event(parts[2], parts[3], parts[4]);
                            break;
                    }
                    if (task != null) {
                        if (parts[1].equals("1")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        }
    }

    private static void saveTasksToFile() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
