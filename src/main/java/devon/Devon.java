package devon;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Devon {

    protected Scanner scanner = new Scanner(System.in);
    private TaskList tasks = new TaskList();
    private Storage storage = new Storage();
    private Parser parser = new Parser();
    private Ui ui = new Ui();

    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UNKNOWN;

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
            ui.displayException(e);
        }
        introduction();
        receiveUserInput();
        try {
            storage.saveTasksToDatabase(tasks);
        } catch (IOException e) {
            ui.displayText("Error! Task(s) could not be saved.");
        }
        goodbye();
    }

    private void introduction() {
        ui.displayText("\t" + "Hello! I'm Devon.\n" + "\t" + "What can I do for you?");
    }

    private void goodbye() {
        ui.displayText("\t" + "Bye. Hope to see you again soon!");
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
                    case FIND:
                        findAction(input);
                        break;
                    default:
                        unknownAction();
                        break;
                }
            } catch (DevonException e) {
                ui.displayException(e);
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

    private void findAction(String input) {
        String keyword = parser.extractContent(input).toLowerCase();
        String results = tasks.search(keyword);
        ui.displayText("Here are the matching tasks in your list:\n\t" + results);
    }

    private void unknownAction() throws DevonUnknownCommandException {
        throw new DevonUnknownCommandException();
    }

    private void addToList(Task task) {
        tasks.addTask(task);
        ui.displayText(
                "\t" + "Got it. I've added this task:\n\t\t"
                        + task + "\n\tNow you have "
                        + tasks.getNumberOfTasks()
                        + " tasks in the list."
        );
    }

    private void printList() {
        ui.displayText(tasks.getListAsString());
    }

    private void markAsDone(int taskIndex) {
        String textResponse = tasks.markAsDone(taskIndex);
        ui.displayText(textResponse);
    }

    private void markAsUndone(int taskIndex) {
        String textResponse = tasks.markAsUndone(taskIndex);
        ui.displayText(textResponse);
    }

    private void deleteTask(int taskIndex) {
        String textResponse = tasks.removeTask(taskIndex);
        ui.displayText(textResponse + "\n\tNow you have " + tasks.getNumberOfTasks() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Devon bot = new Devon();
        bot.start();
    }
}
