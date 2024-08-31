import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Mummy {
    private static final String LOGO = " __  __\n"
            + "|  \\/  |_   _ _ __ ___  _ __ ___  _   _\n"
            + "| |\\/| | | | | '_ ` _ \\| '_ ` _ \\| | | |\n"
            + "| |  | | |_| | | | | | | | | | | | |_| |\n"
            + "|_|  |_|\\__,_|_| |_| |_|_| |_| |_|\\__, |\n"
            + "                                  |___/ \n";

    private static final Storage STORAGE = new Storage("./data/mummy.txt");

    private static final TaskList TASK_LIST = new TaskList(STORAGE.load());

    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE,
        EVENT, DELETE, UNKNOWN
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();
        listen(scanner);
        scanner.close();
    }

    private static void greet() {
        echo("Hello from\n"
                + LOGO + "\n"
                + "What can I do for you?\n");
    }

    private static void listen(Scanner scanner) {
        while (scanner.hasNextLine()) {

            String input = scanner.nextLine();
            Command command;
            HashMap<String, String> arguments = Parser.parse(input);

            try {
                command = Command.valueOf(
                        arguments.getOrDefault("command", "")
                                .toUpperCase()
                );
            } catch (IllegalArgumentException exception) {
                command = Command.UNKNOWN;
            }

            try {
                switch (command) {
                case BYE:
                    onBye();
                    return;
                case LIST:
                    onList();
                    break;
                case MARK:
                    onMark(arguments);
                    break;
                case UNMARK:
                    onUnmark(arguments);
                    break;
                case TODO:
                    onToDo(arguments);
                    break;
                case DEADLINE:
                    onDeadline(arguments);
                    break;
                case EVENT:
                    onEvent(arguments);
                    break;
                case DELETE:
                    onDelete(arguments);
                    break;
                default:
                    throw new MummyException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (MummyException exception) {
                echo("OOPS!!!! " + exception.getMessage());
            }
        }
    }

    private static void onBye() {
        echo("Bye. Hope to see you again soon!\n");
    }

    private static void onList() {
        echo(TASK_LIST.toString());
    }

    private static void onMark(HashMap<String, String> arguments) throws MummyException {
        int taskIndex = parseIntOrDefault(
                arguments.getOrDefault("description", ""),
                -1);

        try {
            TASK_LIST.markTask(taskIndex);
            saveTaskListToStorage();
            echo("Nice! I've marked this task as done:\n\t" + TASK_LIST.get(taskIndex));
        } catch (TaskListException exception) {
            throw new MummyException(exception.getMessage());
        }
    }

    private static void onUnmark(HashMap<String, String> arguments) throws MummyException {
        int taskIndex = parseIntOrDefault(
                arguments.getOrDefault("description", ""),
                -1);
        try {
            TASK_LIST.unmarkTask(taskIndex);
            saveTaskListToStorage();
            echo("OK, I've marked this task as not done yet:\n\t" + TASK_LIST.get(taskIndex));
        } catch (TaskListException exception) {
            throw new MummyException(exception.getMessage());
        }
    }

    private static void onToDo(HashMap<String, String> arguments) throws MummyException {
        // length of "todo " is 5
        try {
            String description = arguments.getOrDefault("description", "");
            addTaskToStore(new ToDo(description));
        } catch (IndexOutOfBoundsException exception) {
            throw new MummyException("description cannot be empty");
        }
    }

    private static void onDeadline(HashMap<String, String> arguments) throws MummyException {
        try {
            String description = arguments.getOrDefault("description", "");
            String dueBy = arguments.getOrDefault("by", "");

            addTaskToStore(new Deadline(description, dueBy));
        } catch (IndexOutOfBoundsException exception) {
            throw new MummyException("Invalid argument: /by is required");
        } catch (DateTimeParseException exception) {
            throw new MummyException("Invalid date format: " + exception.getMessage());
        }
    }

    private static void onEvent(HashMap<String, String> arguments) throws MummyException {
        try {
            String description = arguments.getOrDefault("description", "");
            String from = arguments.getOrDefault("from", "");
            String to = arguments.getOrDefault("to", "");

            addTaskToStore(new Event(description, from, to));
        } catch (IndexOutOfBoundsException exception) {
            throw new MummyException("Invalid argument: /from and /to are required");
        }
    }

    private static void onDelete(HashMap<String, String> arguments) throws MummyException {
        int taskIndex = parseIntOrDefault(
                arguments.getOrDefault("description", ""),
                -1);

        try {
            Task task = TASK_LIST.remove(taskIndex);
            saveTaskListToStorage();
            echo(String.format(
                    "Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.\n",
                    task, TASK_LIST.getCount()
            ));
        } catch (TaskListException exception) {
            throw new MummyException(exception.getMessage());
        }
    }

    private static void addTaskToStore(Task task) {
        TASK_LIST.add(task);
        saveTaskListToStorage();
        echo(String.format(
                "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n",
                task, TASK_LIST.getCount()
        ));
    }

    private static void echo(String message) {
        new Echo(message).execute();
    }

    private static int parseIntOrDefault(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException exception) {
            return defaultValue;
        }
    }

    private static void saveTaskListToStorage() {
        try {
            STORAGE.save(TASK_LIST.toFileRecords());
        } catch (IOException e) {
            System.out.println("Something went wrong when saving to file: "
                    + e.getMessage());
        }
    }
}
