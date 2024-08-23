//https://nus-cs2103-ay2425s1.github.io/website/admin/standardsAndConventions.html
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Xizi {
    private static final String FILE_PATH = "./data/xizi.txt";
    private static final String DIVIDER = "____________________________________________________________";

    // Enums for command types
    private enum CommandType {
        MARK("^mark (\\d+)$"),
        UNMARK("^unmark (\\d+)$"),
        DELETE("^delete\\s+(\\d+)$"),
        TODO("^todo\\s*(.*)$"),
        DEADLINE("^deadline\\s*(.*?)\\s*/by\\s*(.*?)$"),
        EVENT("^event\\s*(.*?)\\s*/from\\s*(.*?)\\s*/to\\s*(.*?)$"),
        LIST("^list$"),
        BYE("^bye$"),
        HELP("^help$"),
        UNKNOWN("");

        private final Pattern pattern;

        CommandType(String regex) {
            this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        }

        public Matcher matcher(String input) {
            return pattern.matcher(input);
        }

        public static CommandType fromInput(String input) {
            for (CommandType type : values()) {
                if (type.matcher(input).matches()) {
                    return type;
                }
            }
            return UNKNOWN;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList actions;
        Storage storage = new Storage(FILE_PATH);

        try {
            List<Task> loadedTasks = storage.load();
            actions = new TaskList(loadedTasks);
        } catch (IOException | XiziException e) {
            printErrorMessage(e.getMessage());
            actions = new TaskList();
        }

        System.out.println(DIVIDER);
        System.out.println(" Hello! I'm Xizi.");
        System.out.println(" What can I do for you?");
        System.out.println(DIVIDER);

        while (true) {
            String userInput = scanner.nextLine().trim();
            CommandType commandType = CommandType.fromInput(userInput);

            try {
                switch (commandType) {
                    case DELETE:
                        handleDelete(actions, storage, userInput);
                        break;
                    case MARK:
                        handleMark(actions, storage, userInput);
                        break;
                    case UNMARK:
                        handleUnmark(actions, storage, userInput);
                        break;
                    case TODO:
                        handleTodo(actions, storage, userInput);
                        break;
                    case DEADLINE:
                        handleDeadline(actions, storage, userInput);
                        break;
                    case EVENT:
                        handleEvent(actions, storage, userInput);
                        break;
                    case LIST:
                        handleList(actions);
                        break;
                    case BYE:
                        handleBye(scanner);
                        return;
                    case HELP:
                        printHelp();
                        break;
                    default:
                        throw new XiziException("Sorry, I didn't understand that command. Type help for all available commands and format.");
                }
            } catch (IOException | XiziException e) {
                printErrorMessage(e.getMessage());
            }
        }
    }

    // Handler methods for each command
    private static void handleDelete(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.DELETE.matcher(userInput);
        if (matcher.matches()) {
            int taskNumber = Integer.parseInt(matcher.group(1)) - 1;
            validateTaskNumber(taskNumber, actions);
            Task deleted = actions.deleteTask(taskNumber);
            storage.saveTasks(actions.getItem());
            System.out.println(DIVIDER);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + deleted);
            System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
            System.out.println(DIVIDER);
        }
    }

    private static void handleMark(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.MARK.matcher(userInput);
        if (matcher.matches()) {
            int taskNumber = Integer.parseInt(matcher.group(1)) - 1;
            validateTaskNumber(taskNumber, actions);
            System.out.println(DIVIDER);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(actions.markTask(taskNumber));
            System.out.println(DIVIDER);
            storage.saveTasks(actions.getItem());
        }
    }

    private static void handleUnmark(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.UNMARK.matcher(userInput);
        if (matcher.matches()) {
            int taskNumber = Integer.parseInt(matcher.group(1)) - 1;
            validateTaskNumber(taskNumber, actions);
            System.out.println(DIVIDER);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(actions.unmarkTask(taskNumber));
            System.out.println(DIVIDER);
            storage.saveTasks(actions.getItem());
        }
    }

    private static void handleTodo(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.TODO.matcher(userInput);
        if (matcher.matches()) {
            String taskDescription = matcher.group(1).trim();
            if (taskDescription.isEmpty()) {
                throw new XiziException("The description of a todo cannot be empty. Type help to see the formats required.");
            }
            actions.addTask(new Todo(taskDescription));
            storage.appendTask(new Todo(taskDescription));
            System.out.println(DIVIDER);
            System.out.println("Got it. I've added this task:");
            System.out.println("  [T][ ] " + taskDescription);
            System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
            System.out.println(DIVIDER);
        }
    }

    private static void handleDeadline(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.DEADLINE.matcher(userInput);
        if (matcher.matches()) {
            String taskDescription = matcher.group(1).trim();
            String deadline = matcher.group(2).trim();
            if (taskDescription.isEmpty() || deadline.isEmpty()) {
                throw new XiziException("The description or time of a deadline cannot be empty.Type help to see the formats required.");
            }
            actions.addTask(new Deadline(taskDescription, deadline));
            storage.appendTask(new Deadline(taskDescription, deadline));
            System.out.println(DIVIDER);
            System.out.println("Got it. I've added this task:");
            System.out.println("  [D][ ] " + taskDescription + " (by: " + deadline + ")");
            System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
            System.out.println(DIVIDER);
        }
    }

    private static void handleEvent(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.EVENT.matcher(userInput);
        if (matcher.matches()) {
            String taskDescription = matcher.group(1).trim();
            String fromTime = matcher.group(2).trim();
            String toTime = matcher.group(3).trim();
            if (taskDescription.isEmpty() || fromTime.isEmpty() || toTime.isEmpty()) {
                throw new XiziException("The description, from or to time of an event cannot be empty.Type help to see the formats required.");
            }
            actions.addTask(new Event(taskDescription, fromTime, toTime));
            storage.appendTask(new Event(taskDescription, fromTime, toTime));
            System.out.println(DIVIDER);
            System.out.println("Got it. I've added this task:");
            System.out.println("  [E][ ] " + taskDescription + " (from: " + fromTime + " to: " + toTime + ")");
            System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
            System.out.println(DIVIDER);
        }
    }

    private static void handleList(TaskList actions) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        actions.printActions();
        System.out.println(DIVIDER);
    }

    private static void handleBye(Scanner scanner) {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
        scanner.close();
    }

    private static void validateTaskNumber(int taskNumber, TaskList actions) throws XiziException {
        if (taskNumber < 0 || taskNumber >= actions.getSize()) {
            throw new XiziException("The task number does not exist. You have " + actions.getSize() + " tasks in total.");
        }
    }

    private static void printErrorMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(" OOPS!!! " + message);
        System.out.println(DIVIDER);
    }

    private static void printHelp() {
        System.out.println(DIVIDER);
        System.out.println("Here are the available commands and their formats:");
        System.out.println();
        System.out.println("1. list");
        System.out.println("   - Displays all tasks in your list.");
        System.out.println();
        System.out.println("2. todo <task_description>");
        System.out.println("   - Adds a new 'todo' task.");
        System.out.println("     Example: todo read a book");
        System.out.println();
        System.out.println("3. deadline <task_description> /by <deadline>");
        System.out.println("   - Adds a new 'deadline' task.");
        System.out.println("     Example: deadline submit report /by Monday");
        System.out.println();
        System.out.println("4. event <task_description> /from <start_time> /to <end_time>");
        System.out.println("   - Adds a new 'event' task.");
        System.out.println("     Example: event project meeting /from Monday 2pm /to Monday 4pm");
        System.out.println();
        System.out.println("5. mark <task_number>");
        System.out.println("   - Marks the specified task as completed.");
        System.out.println("     Example: mark 3");
        System.out.println();
        System.out.println("6. unmark <task_number>");
        System.out.println("   - Unmarks the specified task as not completed.");
        System.out.println("     Example: unmark 3");
        System.out.println();
        System.out.println("7. delete <task_number>");
        System.out.println("   - Deletes the specified task.");
        System.out.println("     Example: delete 3");
        System.out.println();
        System.out.println("8. bye");
        System.out.println("   - Exits the program.");
        System.out.println();
        System.out.println("9. help");
        System.out.println("   - Displays this help message.");
        System.out.println(DIVIDER);
    }
}
