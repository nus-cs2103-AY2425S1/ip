package shrimp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import shrimp.command.AddCommand;
import shrimp.command.ClearCommand;
import shrimp.command.DeleteCommand;
import shrimp.command.ExitCommand;
import shrimp.command.FindCommand;
import shrimp.command.ListCommand;
import shrimp.command.MarkCommand;
import shrimp.exception.ShrimpException;
import shrimp.task.Deadline;
import shrimp.task.Event;
import shrimp.task.Task;
import shrimp.task.TaskList;
import shrimp.task.Todo;
import shrimp.utility.Parser;
import shrimp.utility.Parser.CommandType;
import shrimp.utility.Storage;
import shrimp.utility.Ui;

/**
 * The {@code Shrimp} class serves as the entry point for the Shrimp chatbot application.
 * It handles the main program flow, including user interaction, task management,
 * and command execution.
 *
 * <p> The Shrimp chatbot allows users to manage a list of tasks through various commands,
 * such as adding, deleting, marking tasks as complete, and searching for tasks.
 * The application also supports deadlines and events with time ranges.
 */
public class Shrimp {

    // Static fields and constants

    /**
     * The status of whether a task is done, initialized to false.
     */
    private static final Boolean hasDone = false;

    /**
     * The instance of the {@code Ui} class used to interact with the user interface.
     */
    private static final Ui ui = new Ui();

    /**
     * The task list that stores all tasks managed by the chatbot.
     */
    private static TaskList taskList = new TaskList();

    /**
     * A regular expression to ensure input contains only alphanumeric characters and spaces.
     */
    private static final String ALLOWED_CHARACTERS_REGEX = "^[\\w\\s]+$"; // Only allows alphanumeric and spaces

    /**
     * Starts the Shrimp chatbot and loads the saved task list, if available.
     * If loading fails, an empty task list is initialized.
     *
     * @return a message indicating the result of the task loading process.
     */
    public String runChatBot() {
        try {
            taskList = Storage.loadTasks();
            return "";
        } catch (IOException | ShrimpException e) {
            taskList = new TaskList();
            return ui.printError("Couldn't load tasks... Starting with an empty list.");
        }
    }

    /**
     * Displays the welcome message to the user when the chatbot is started.
     *
     * @return the welcome message string.
     */
    public String showWelcome() {
        return ui.printWelcome();
    }

    /**
     * Processes the user's input and executes the corresponding command.
     * If the input is invalid, an error message is returned.
     *
     * @param userInput the user's input command string.
     * @return the result of the command execution or an error message.
     */
    public String getResponse(String userInput) {
        try {
            if (userInput == null || userInput.isEmpty()) {
                throw new ShrimpException.InvalidCommandException();
            }

            userInput = userInput.trim();

            CommandType commandType = Parser.parseCommand(userInput);
            return switch (commandType) {
            case BYE -> fetchExit();
            case LIST -> fetchList();
            case MARK -> fetchMark(userInput, commandType, true);
            case UNMARK -> fetchMark(userInput, commandType, false);
            case DELETE -> fetchDelete(userInput, commandType);
            case ADD -> fetchAdd(userInput, commandType);
            case DEADLINE -> fetchDeadline(userInput, commandType);
            case EVENT -> fetchEvent(userInput, commandType);
            case CLEAR -> fetchClear();
            case FIND -> fetchFind(userInput, commandType);
            default -> throw new ShrimpException.InvalidCommandException();
            };
        } catch (ShrimpException e) {
            return ui.printError(e.getMessage() + String.format(" (%s)", e.getErrorCode()));
        } catch (Exception e) {
            return ui.printError("Something went wrong... Try again!");
        }
    }

    /**
     * Handles the "find" command by searching for tasks that match the given keyword.
     *
     * @param userInput   the user's input containing the find command.
     * @param commandType the type of command being processed.
     * @return the result of the find operation.
     * @throws ShrimpException.MissingArgumentException if the keyword is missing.
     */
    private static String fetchFind(String userInput, CommandType commandType)
            throws ShrimpException.MissingArgumentException {
        if (userInput.length() <= 5) {
            throw new ShrimpException.MissingArgumentException(commandType);
        }
        String keyword = userInput.substring(5).trim();
        FindCommand findCommand = new FindCommand(keyword);
        return findCommand.run(taskList, ui);
    }

    /**
     * Clears all tasks from the task list.
     *
     * @return the result of the clear operation.
     */
    private static String fetchClear() {
        ClearCommand clearCommand = new ClearCommand();
        return clearCommand.run(taskList, ui);
    }

    /**
     * Adds an event task with a description and time range to the task list.
     *
     * @param userInput   the user's input containing the event command.
     * @param commandType the type of command being processed.
     * @return the result of the event addition.
     * @throws ShrimpException if the input format is incorrect or the time range is invalid.
     */
    private static String fetchEvent(String userInput, CommandType commandType) throws ShrimpException {
        if (userInput.length() <= 6 || !userInput.contains("/from") || !userInput.contains("/to")) {
            throw new ShrimpException.MissingArgumentException(commandType);
        }
        String[] eventDetails = userInput.split("/from | /to ");
        String eventDescription = eventDetails[0].substring(6).trim(); // Extracting the task description
        LocalDateTime from = getDateTime(eventDetails[1].trim());
        LocalDateTime to = getDateTime(eventDetails[2].trim());
        if (to.isBefore(from)) {
            throw new ShrimpException.InvalidCommandException();
        }
        Task newEvent = new Event(eventDescription, from, to, hasDone);
        AddCommand addEvent = new AddCommand(newEvent);
        return addEvent.run(taskList, ui);
    }

    /**
     * Adds a deadline task with a description and due date to the task list.
     *
     * @param userInput   the user's input containing the deadline command.
     * @param commandType the type of command being processed.
     * @return the result of the deadline addition.
     * @throws ShrimpException if the input format is incorrect.
     */
    private static String fetchDeadline(String userInput, CommandType commandType) throws ShrimpException {
        if (userInput.length() <= 9 || !userInput.contains("/by")) {
            throw new ShrimpException.MissingArgumentException(commandType);
        }
        String[] deadlineDetails = userInput.split("/by ");
        String deadlineDescription = deadlineDetails[0].substring(9).trim(); // Extracting the task description
        LocalDateTime by = getDateTime(deadlineDetails[1].trim());
        Task newDeadline = new Deadline(deadlineDescription, by, hasDone);
        AddCommand addDeadline = new AddCommand(newDeadline);
        return addDeadline.run(taskList, ui);
    }

    /**
     * Adds a to-do task to the task list.
     *
     * @param userInput   the user's input containing the add command.
     * @param commandType the type of command being processed.
     * @return the result of the to-do addition.
     * @throws ShrimpException if the input format is incorrect or contains invalid characters.
     */
    private static String fetchAdd(String userInput, CommandType commandType) throws ShrimpException {
        if (userInput.length() <= 5) {
            throw new ShrimpException.MissingArgumentException(commandType);
        }
        String input = userInput.substring(5).trim();
        validateInput(input);
        Todo newTodo = new Todo(input, hasDone); //creates a new Task.Task object
        AddCommand addTodo = new AddCommand(newTodo);
        return addTodo.run(taskList, ui);
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param userInput   the user's input containing the delete command.
     * @param commandType the type of command being processed.
     * @return the result of the delete operation.
     * @throws ShrimpException if the task number is invalid or out of bounds.
     */
    private static String fetchDelete(String userInput, CommandType commandType) throws ShrimpException {
        int indexDelete = getTaskNumber(userInput, commandType);
        if (indexDelete > taskList.size()) {
            throw new ShrimpException.ArrayIndexOutOfBoundException();
        } else if (taskList.isEmpty()) {
            throw new ShrimpException.EmptyArrayException();
        }
        DeleteCommand deleteCommand = new DeleteCommand(indexDelete);
        return deleteCommand.run(taskList, ui);
    }

    /**
     * Marks or unmarks a task in the task list as done/undone.
     *
     * @param userInput   the user's input containing the mark/unmark command.
     * @param commandType the type of command being processed.
     * @param toMark      {@code true} if the task is to be marked as done, {@code false} if to unmark.
     * @return the result of the mark/unmark operation.
     * @throws ShrimpException if the task number is invalid or out of bounds.
     */
    private static String fetchMark(String userInput, CommandType commandType, boolean toMark) throws ShrimpException {
        int indexMark = getTaskNumber(userInput, commandType);
        if (indexMark > taskList.size()) {
            throw new ShrimpException.ArrayIndexOutOfBoundException();
        } else if (taskList.isEmpty()) {
            throw new ShrimpException.EmptyArrayException();
        }
        MarkCommand markCommand = new MarkCommand(indexMark, toMark);
        return markCommand.run(taskList, ui);
    }

    /**
     * Displays the list of tasks in the task list.
     *
     * @return the result of the list command.
     * @throws ShrimpException.EmptyArrayException if the task list is empty.
     */
    private static String fetchList() throws ShrimpException.EmptyArrayException {
        if (taskList.isEmpty()) {
            throw new ShrimpException.EmptyArrayException();
        }
        ListCommand listCommand = new ListCommand();
        return listCommand.run(taskList, ui);
    }

    /**
     * Exits the Shrimp chatbot and saves the current task list.
     *
     * @return the result of the exit command.
     */
    private static String fetchExit() {
        ExitCommand exitCommand = new ExitCommand();
        return exitCommand.run(taskList, ui);
    }

    /**
     * Saves the current task list to persistent storage.
     */
    public static void saveTasks() {
        Storage.saveTasks(taskList);
    }

    /**
     * Extracts the task number from the user input for MARK, UNMARK, or DELETE commands.
     *
     * @param userInput the user's input containing the command and task number.
     * @param type      the type of command being processed.
     * @return the task number (zero-based index).
     * @throws ShrimpException if the task number is missing or not a valid integer.
     */
    static int getTaskNumber(String userInput, Parser.CommandType type) throws ShrimpException {
        assert userInput != null : "userInput is null";
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ShrimpException.MissingArgumentException(type);
        } catch (NumberFormatException e) {
            throw new ShrimpException.NumberFormatException();
        }
    }

    /**
     * Parses a {@code String} into a {@code LocalDateTime} object using the defined date/time pattern.
     *
     * @param input the date/time string to be parsed.
     * @return the parsed {@code LocalDateTime} object.
     * @throws ShrimpException if the date/time format is invalid.
     */
    static LocalDateTime getDateTime(String input) throws ShrimpException {
        try {
            return LocalDateTime.parse(input, Parser.PATTERN);
        } catch (DateTimeParseException e) {
            throw new ShrimpException.InvalidDateTimeException();
        }
    }

    /**
     * Validates the user's input string to ensure it contains only allowed characters.
     *
     * @param input the input string to be validated.
     * @throws ShrimpException if the input contains invalid characters.
     */
    private static void validateInput(String input) throws ShrimpException {
        if (!input.matches(ALLOWED_CHARACTERS_REGEX)) {
            throw new ShrimpException.InvalidCharacterException();
        }
    }
}
