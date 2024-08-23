import java.util.ArrayList;
import java.util.Scanner;

public class Yapper {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
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
                        return;

                    case LIST:
                        handleList();
                        break;

                    case MARK:
                        handleMark(userInputParts);
                        break;

                    case UNMARK:
                        handleUnmark(userInputParts);
                        break;

                    case TODO:
                        handleTodo(userInputParts);
                        break;

                    case DEADLINE:
                        handleDeadline(userInputParts);
                        break;

                    case EVENT:
                        handleEvent(userInputParts);
                        break;

                    case DELETE:
                        handleDelete(userInputParts);
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
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void printErrorMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    private static void handleBye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void handleList() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
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
        System.out.println(" Nice! I've marked this task as done:");
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
        System.out.println(" OK, I've marked this task as not done yet:");
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
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
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
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleEvent(String[] userInputParts) throws YapperException {
        if (userInputParts.length < 2) {
            throw new EmptyDescriptionException("event");
        }
        String[] details = userInputParts[1].split(" /from ");
        if (details.length < 2) {
            throw new YapperException("Please specify the event time using the format: event [task] /from [start time] /to [end time]");
        }
        String[] fromTo = details[1].split(" /to ");
        if (fromTo.length < 2) {
            throw new YapperException("Please specify both the start and end time for the event.");
        }
        Task task = new Event(details[0], fromTo[0], fromTo[1]);
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
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
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
