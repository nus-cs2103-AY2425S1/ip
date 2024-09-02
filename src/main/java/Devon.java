import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Devon {

    protected Scanner scanner = new Scanner(System.in);
    private TaskList tasks = new TaskList();
    private Storage storage = new Storage();
    private Parser parser = new Parser();

    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN;

        public static Command fromStringToEnum(String command) {
            try {
                return Command.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

    private void start() {
        ArrayList<String> stringListOfTasks = storage.loadTasksFromDatabase();
        try {
            tasks.initialiseLoadTasks(stringListOfTasks);
        } catch (DevonReadDatabaseException e) {
            System.out.println(e);
        }
        introduction();
        receiveUserInput();
        try {
            storage.saveTasksToDatabase(tasks);
        } catch (IOException e) {
            System.out.println("Error! Task(s) could not be saved.");
        }
        goodbye();
    }

    private void printLongLine() {
        String LINE_SEPARATOR = "____________________";
        System.out.println("\t" + LINE_SEPARATOR);
    }

    private void introduction() {
        printLongLine();
        System.out.println("\t" + "Hello! I'm Devon.");
        System.out.println("\t" + "What can I do for you?");
        printLongLine();
    }

    private void goodbye() {
        printLongLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLongLine();
    }

    private void receiveUserInput() {
        while (true) {
            String input = scanner.nextLine();
            Command command = Command.fromStringToEnum(parser.extractCommand(input));

            try {
                switch (command) {
                    case BYE:
                        return;
                    case LIST:
                        printList();
                        break;
                    case MARK:
                        markAction(input);
                        break;
                    case UNMARK:
                        unmarkAction(input);
                        break;
                    case TODO:
                        todoAction(input);
                        break;
                    case DEADLINE:
                        deadlineAction(input);
                        break;
                    case EVENT:
                        eventAction(input);
                        break;
                    case DELETE:
                        deleteAction(input);
                        break;
                    default:
                        unknownAction();
                        break;
                }
            } catch (DevonException e) {
                printLongLine();
                System.out.println("\t" + e);
                printLongLine();
            }
        }
    }

    private void markAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex;
        try {
            taskIndex = parser.extractTaskIndex(input) - 1;
        } catch (NumberFormatException e) {
            throw new DevonInvalidTaskNumberException();
        }
        if (taskIndex < 0 || taskIndex >= tasks.getNumberOfTasks()) {
            throw new DevonInvalidTaskNumberException();
        }
        markAsDone(taskIndex);
    }

    private void unmarkAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex;
        try {
            taskIndex = parser.extractTaskIndex(input) - 1;
        } catch (NumberFormatException e) {
            throw new DevonInvalidTaskNumberException();
        }
        if (taskIndex < 0 || taskIndex >= tasks.getNumberOfTasks()) {
            throw new DevonInvalidTaskNumberException();
        }
        markAsUndone(taskIndex);
    }

    private void todoAction(String input) {
        String description = parser.extractTodo(input);
        addToList(new Todo(description));
    }

    private void deadlineAction(String input) throws DevonInvalidDeadlineException, DevonInvalidDateTimeException {
        String[] contents = parser.extractDeadline(input);
        String description = contents[0];
        String by = contents[1];
        try {
            LocalDateTime byDateTime = LocalDateTime.parse(by, Storage.DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT);
            addToList(new Deadline(description, byDateTime));
        } catch (DateTimeParseException e) {
            throw new DevonInvalidDateTimeException();
        }
    }

    private void eventAction(String input) throws DevonInvalidEventException, DevonInvalidDateTimeException {
        String[] contents = parser.extractEvent(input);
        String description = contents[0];
        String from = contents[1];
        String to = contents[2];
        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(from, Storage.DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT);
            LocalDateTime toDateTime = LocalDateTime.parse(to, Storage.DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT);
            addToList(new Event(description, fromDateTime, toDateTime));
        } catch (DateTimeParseException e) {
            throw new DevonInvalidDateTimeException();
        }
    }

    private void deleteAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex;
        try {
            taskIndex = parser.extractTaskIndex(input) - 1;
        } catch (NumberFormatException e) {
            throw new DevonInvalidTaskNumberException();
        }
        if (taskIndex < 0 || taskIndex >= tasks.getNumberOfTasks()) {
            throw new DevonInvalidTaskNumberException();
        }
        deleteTask(taskIndex);
    }

    private void unknownAction() throws DevonUnknownCommandException {
        throw new DevonUnknownCommandException();
    }

    private void addToList(Task task) {
        tasks.addTask(task);
        this.printLongLine();
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t\t" + task);
        printNumberOfTasks();
        this.printLongLine();
    }

    private void printList() {
        this.printLongLine();
        tasks.printList();
        this.printLongLine();
    }

    private void printNumberOfTasks() {
        System.out.println("\t" + "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.");
    }

    private void markAsDone(int taskIndex) {
        printLongLine();
        tasks.markAsDone(taskIndex);
        printLongLine();
    }

    private void markAsUndone(int taskIndex) {
        printLongLine();
        tasks.markAsUndone(taskIndex);
        printLongLine();
    }

    private void deleteTask(int taskIndex) {
        tasks.removeTask(taskIndex);
        printLongLine();
        printNumberOfTasks();
        printLongLine();
    }

    public static void main(String[] args) {
        Devon bot = new Devon();
        bot.start();
    }
}
