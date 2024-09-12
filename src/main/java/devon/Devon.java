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
    protected void start() {
        ArrayList<String> stringListOfTasks = storage.loadTasksFromDatabase();
        try {
            tasks.initialiseLoadTasks(stringListOfTasks);
        } catch (DevonReadDatabaseException e) {
            ui.displayException(e);
        }
    }

    /**
     * Displays the introduction message to the user.
     */
    protected String introduction() {
        return "\t" + "Hello! I'm Devon.\n" + "\t" + "What can I do for you?";
    }

    /**
     * Displays the goodbye message to the user.
     */
    private String goodbye() {
        try {
            storage.saveTasksToDatabase(tasks);
        } catch (IOException e) {
            ui.displayText("Error! Task(s) could not be saved.");
        }
        return "\t" + "Bye. Hope to see you again soon!";
    }

    /**
     * Continuously receives user input until the "BYE" command is given.
     * Based on the input, different actions are performed.
     */
    protected String getResponse(String input) {
        Command command = Command.fromStringToEnum(parser.extractCommand(input));
        String response;

        try {
            switch (command) {
            case BYE:
                return goodbye();
            case LIST:
                return getListAsString();
            case MARK:
                return markAction(input);
            case UNMARK:
                return unmarkAction(input);
            case TODO:
                return todoAction(input);
            case DEADLINE:
                return deadlineAction(input);
            case EVENT:
                return eventAction(input);
            case DELETE:
                return deleteAction(input);
            case FIND:
                return findAction(input);
            default:
                unknownAction();
            }
        } catch (DevonException e) {
            return e.toString();
        }
        return "Error";
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param input The user input specifying which task to mark as done.
     * @throws DevonInvalidTaskNumberException If the task number provided is invalid.
     */
    private String markAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex;
        try {
            taskIndex = parser.extractTaskIndex(input) - 1;
        } catch (NumberFormatException e) {
            throw new DevonInvalidTaskNumberException();
        }
        if (taskIndex < 0 || taskIndex >= tasks.getNumberOfTasks()) {
            throw new DevonInvalidTaskNumberException();
        }
        return markAsDone(taskIndex);
    }

    /**
     * Marks a task as undone based on user input.
     *
     * @param input The user input specifying which task to mark as undone.
     * @throws DevonInvalidTaskNumberException If the task number provided is invalid.
     */
    private String unmarkAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex;
        try {
            taskIndex = parser.extractTaskIndex(input) - 1;
        } catch (NumberFormatException e) {
            throw new DevonInvalidTaskNumberException();
        }
        if (taskIndex < 0 || taskIndex >= tasks.getNumberOfTasks()) {
            throw new DevonInvalidTaskNumberException();
        }
        return markAsUndone(taskIndex);
    }

    /**
     * Adds a new Todo task to the list based on user input.
     *
     * @param input The user input specifying the description of the Todo task.
     */
    private String todoAction(String input) {
        String description = parser.extractTodo(input);
        return addToList(new Todo(description));
    }

    /**
     * Adds a new Deadline task to the list based on user input.
     *
     * @param input The user input specifying the description and due date of the Deadline task.
     * @throws DevonInvalidDeadlineException If the deadline format is invalid.
     * @throws DevonInvalidDateTimeException If the date/time format is invalid.
     */
    private String deadlineAction(String input) throws DevonInvalidDeadlineException, DevonInvalidDateTimeException {
        String[] contents = parser.extractDeadline(input);
        String description = contents[0];
        String by = contents[1];

        try {
            LocalDateTime byDateTime = LocalDateTime.parse(by, Storage.DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT);
            return addToList(new Deadline(description, byDateTime));
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
    private String eventAction(String input) throws DevonInvalidEventException, DevonInvalidDateTimeException {
        String[] contents = parser.extractEvent(input);
        String description = contents[0];
        String from = contents[1];
        String to = contents[2];

        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(from, Storage.DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT);
            LocalDateTime toDateTime = LocalDateTime.parse(to, Storage.DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT);
            return addToList(new Event(description, fromDateTime, toDateTime));
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
    private String deleteAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex;

        try {
            taskIndex = parser.extractTaskIndex(input) - 1;
        } catch (NumberFormatException e) {
            throw new DevonInvalidTaskNumberException();
        }
        if (taskIndex < 0 || taskIndex >= tasks.getNumberOfTasks()) {
            throw new DevonInvalidTaskNumberException();
        }

        return deleteTask(taskIndex);
    }

    /**
     * Finds and displays tasks that match a given keyword.
     * <p>
     * This method extracts the content from the provided input string, converts it to lowercase, and searches
     * for matching tasks within the task list. The results are then displayed to the user.
     *
     * @param input The input string containing the keyword for searching tasks.
     */
    private String findAction(String input) {
        String keyword = parser.extractContent(input).toLowerCase();
        String results = tasks.search(keyword);
        return "Here are the matching tasks in your list:\n\t" + results;
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
    private String addToList(Task task) {
        tasks.addTask(task);
        return "\t" + "Got it. I've added this task:\n\t\t"
                        + task + "\n\tNow you have "
                        + tasks.getNumberOfTasks()
                        + " tasks in the list.";
    }

    /**
     * Prints the current task list to the user.
     */
    private String getListAsString() {
        return tasks.getListAsString();
    }

    /**
     * Marks a task as done and displays a confirmation message.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    private String markAsDone(int taskIndex) {
        return tasks.markAsDone(taskIndex);
    }

    /**
     * Marks a task as undone and displays a confirmation message.
     *
     * @param taskIndex The index of the task to mark as undone.
     */
    private String markAsUndone(int taskIndex) {
        return tasks.markAsUndone(taskIndex);
    }

    /**
     * Deletes a task from the list and displays a confirmation message.
     *
     * @param taskIndex The index of the task to delete.
     */
    private String deleteTask(int taskIndex) {
        String textResponse = tasks.removeTask(taskIndex);
        return textResponse + "\n\tNow you have " + tasks.getNumberOfTasks() + " tasks in the list.";
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
