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

    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN;

        public static Command fromString(String command) {
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

    private String detectCommand(String msg) {
        String[] parts = msg.split(" ");
        return parts[0];
    }

    private String detectContent(String msg) {
        String[] parts = msg.split(" ");
        return parts.length > 1
                ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))
                : "Error";
    }

    private void receiveUserInput() {
        while (true) {
            String input = scanner.nextLine();
            Command command = Command.fromString(detectCommand(input));

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
            taskIndex = Integer.parseInt(detectContent(input)) - 1;
        } catch (NumberFormatException e) {
            throw new DevonInvalidTaskNumberException();
        }
        if (taskIndex < 0 || taskIndex >= tasks.getNumberOfTasks()) {
            throw new DevonInvalidTaskNumberException();
        }
        markAsDone(tasks.getTask(taskIndex));
    }

    private void unmarkAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(detectContent(input)) - 1;
        } catch (NumberFormatException e) {
            throw new DevonInvalidTaskNumberException();
        }
        if (taskIndex < 0 || taskIndex >= tasks.getNumberOfTasks()) {
            throw new DevonInvalidTaskNumberException();
        }
        markAsUndone(tasks.getTask(taskIndex));
    }

    private void todoAction(String input) {
        String description = detectContent(input).trim();
        addToList(new Todo(description));
    }

    private void deadlineAction(String input) throws DevonInvalidDeadlineException, DevonInvalidDateTimeException {
        String content = detectContent(input);
        if (!content.contains("/by")) {
            throw new DevonInvalidDeadlineException();
        }
        String[] parts = content.split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();

        try {
            LocalDateTime byDateTime = LocalDateTime.parse(by, Storage.DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT);
            addToList(new Deadline(description, byDateTime));
        } catch (DateTimeParseException e) {
            throw new DevonInvalidDateTimeException();
        }
    }

    private void eventAction(String input) throws DevonInvalidEventException, DevonInvalidDateTimeException {
        String content = detectContent(input);
        if (!(content.contains("/from") && content.contains("/to"))) {
            throw new DevonInvalidEventException();
        }
        String[] partsFrom = content.split("/from", 2);
        String[] partsTo = partsFrom[1].split("/to", 2);
        String description = partsFrom[0].trim();
        String from = partsTo[0].trim();
        String to = partsTo[1].trim();

        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(from, Storage.DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT);
            LocalDateTime toDateTime = LocalDateTime.parse(to, Storage.DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT);
            addToList(new Event(description, fromDateTime, toDateTime));
        } catch (DateTimeParseException e) {
            throw new DevonInvalidDateTimeException();
        }
    }

    private void deleteAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex = Integer.parseInt(detectContent(input)) - 1;
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

    private void markAsDone(Task task) {
        printLongLine();
        task.markAsDone();
        printLongLine();
    }

    private void markAsUndone(Task task) {
        printLongLine();
        task.markAsUndone();
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
