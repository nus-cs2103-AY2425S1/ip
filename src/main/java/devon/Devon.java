package devon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Devon bot, which manages a task list.
 * Devon can add, delete, mark, unmark tasks, and save/load tasks from a database.
 */
public class Devon {

    protected Scanner scanner = new Scanner(System.in);
    private TaskList tasks = new TaskList();
    private Storage storage = new Storage();
    private Parser parser = new Parser();
    private Ui ui = new Ui();

    /**
     * Enum to represent the various commands the user can input.
     */
    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UNKNOWN;

        /**
         * Converts a string command to the corresponding enum value.
         *
         * @param command The command string input by the user.
         * @return The corresponding Command enum value.
         */
        public static Command fromStringToEnum(String command) {
            try {
                return Command.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

    /**
     * Starts the Devon bot. Loads tasks from the database, receives user input, and saves tasks back to the database.
     */
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

    /**
     * Displays the introduction message to the user.
     */
    private void introduction() {
        ui.displayText("\t" + "Hello! I'm Devon.\n" + "\t" + "What can I do for you?");
    }

    /**
     * Displays the goodbye message to the user.
     */
    private void goodbye() {
        ui.displayText("\t" + "Bye. Hope to see you again soon!");
    }

    /**
     * Continuously receives user input until the "BYE" command is given.
     * Based on the input, different actions are performed.
     */
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

    /**
     * Marks a task as done based on user input.
     *
     * @param input The user input specifying which task to mark as done.
     * @throws DevonInvalidTaskNumberException If the task number provided is invalid.
     */
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

    /**
     * Marks a task as undone based on user input.
     *
     * @param input The user input specifying which task to mark as undone.
     * @throws DevonInvalidTaskNumberException If the task number provided is invalid.
     */
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

    /**
     * Adds a new Todo task to the list based on user input.
     *
     * @param input The user input specifying the description of the Todo task.
     */
    private void todoAction(String input) {
        String description = parser.extractTodo(input);
        addToList(new Todo(description));
    }

    /**
     * Adds a new Deadline task to the list based on user input.
     *
     * @param input The user input specifying the description and due date of the Deadline task.
     * @throws DevonInvalidDeadlineException If the deadline format is invalid.
     * @throws DevonInvalidDateTimeException If the date/time format is invalid.
     */
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

    /**
     * Adds a new Event task to the list based on user input.
     *
     * @param input The user input specifying the description, start time, and end time of the Event task.
     * @throws DevonInvalidEventException If the event format is invalid.
     * @throws DevonInvalidDateTimeException If the date/time format is invalid.
     */
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

    /**
     * Deletes a task from the list based on user input.
     *
     * @param input The user input specifying which task to delete.
     * @throws DevonInvalidTaskNumberException If the task number provided is invalid.
     */
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

    /**
     * Finds and displays tasks that match a given keyword.
     * <p>
     * This method extracts the content from the provided input string, converts it to lowercase, and searches
     * for matching tasks within the task list. The results are then displayed to the user.
     *
     * @param input The input string containing the keyword for searching tasks.
     */
    private void findAction(String input) {
        String keyword = parser.extractContent(input).toLowerCase();
        String results = tasks.search(keyword);
        ui.displayText("Here are the matching tasks in your list:\n\t" + results);
    }

    /**
     * Handles the case where the user inputs an unknown command.
     *
     * @throws DevonUnknownCommandException If the command is unknown.
     */
    private void unknownAction() throws DevonUnknownCommandException {
        throw new DevonUnknownCommandException();
    }

    /**
     * Adds a task to the task list and displays a confirmation message.
     *
     * @param task The task to add to the list.
     */
    private void addToList(Task task) {
        tasks.addTask(task);
        ui.displayText(
                "\t" + "Got it. I've added this task:\n\t\t"
                        + task + "\n\tNow you have "
                        + tasks.getNumberOfTasks()
                        + " tasks in the list."
        );
    }

    /**
     * Prints the current task list to the user.
     */
    private void printList() {
        ui.displayText(tasks.getListAsString());
    }

    /**
     * Marks a task as done and displays a confirmation message.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    private void markAsDone(int taskIndex) {
        String textResponse = tasks.markAsDone(taskIndex);
        ui.displayText(textResponse);
    }

    /**
     * Marks a task as undone and displays a confirmation message.
     *
     * @param taskIndex The index of the task to mark as undone.
     */
    private void markAsUndone(int taskIndex) {
        String textResponse = tasks.markAsUndone(taskIndex);
        ui.displayText(textResponse);
    }

    /**
     * Deletes a task from the list and displays a confirmation message.
     *
     * @param taskIndex The index of the task to delete.
     */
    private void deleteTask(int taskIndex) {
        String textResponse = tasks.removeTask(taskIndex);
        ui.displayText(textResponse + "\n\tNow you have " + tasks.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Main method to start the Devon bot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Devon bot = new Devon();
        bot.start();
    }
}
