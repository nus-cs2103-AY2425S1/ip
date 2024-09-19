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
 */
public class Shrimp {

    private static final Boolean hasDone = false;
    private static final Ui ui = new Ui();
    private static TaskList taskList = new TaskList();

    /**
     * Runs the chatbot, handling user input and executing commands.
     * The loop continues until the user issues the "bye" command.
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

    public String showWelcome() {
        return ui.printWelcome();
    }

    public String getResponse(String userInput) {
        try {
            if (userInput == null || userInput.isEmpty()) {
                throw new ShrimpException.InvalidCommandException();
            }

            CommandType commandType = Parser.parseCommand(userInput);
            switch (commandType) {
            case BYE: //exits the program
                return fetchExit();
            case LIST:
                return fetchList();
            case MARK:
                return fetchMark(userInput, commandType, true);
            case UNMARK:
                return fetchMark(userInput, commandType, false);
            case DELETE:
                return fetchDelete(userInput, commandType);
            case ADD:
                return fetchAdd(userInput, commandType);
            case DEADLINE:
                return fetchDeadline(userInput, commandType);
            case EVENT:
                return fetchEvent(userInput, commandType);
            case CLEAR:
                return fetchClear();
            case FIND:
                return fetchFind(userInput, commandType);
            default:
                throw new ShrimpException.InvalidCommandException();
            }
        } catch (ShrimpException e) {
            return ui.printError(e.getMessage() + String.format(" (%s)", e.getErrorCode()));
        } catch (Exception e) {
            return ui.printError("Something went wrong... Try again!");
        }
    }

    private static String fetchFind(String userInput, CommandType commandType) throws ShrimpException.MissingArgumentException {
        if (userInput.length() <= 5) {
            throw new ShrimpException.MissingArgumentException(commandType);
        }
        String keyword = userInput.substring(5).trim();
        FindCommand findCommand = new FindCommand(keyword);
        return findCommand.run(taskList, ui);
    }

    private static String fetchClear() {
        ClearCommand clearCommand = new ClearCommand();
        return clearCommand.run(taskList, ui);
    }

    private static String fetchEvent(String userInput, CommandType commandType) throws ShrimpException {
        if (userInput.length() <= 6 || !userInput.contains("/from") || !userInput.contains("/to")) {
            throw new ShrimpException.MissingArgumentException(commandType);
        }
        String[] eventDetails = userInput.split("/from | /to ");
        String eventDescription = eventDetails[0].substring(6); // Extracting the task description
        LocalDateTime from = getDateTime(eventDetails[1].trim());
        LocalDateTime to = getDateTime(eventDetails[2].trim());
        Task newEvent = new Event(eventDescription, from, to, hasDone);
        AddCommand addEvent = new AddCommand(newEvent);
        return addEvent.run(taskList, ui);
    }

    private static String fetchDeadline(String userInput, CommandType commandType) throws ShrimpException {
        if (userInput.length() <= 9 || !userInput.contains("/by")) {
            throw new ShrimpException.MissingArgumentException(commandType);
        }
        String[] deadlineDetails = userInput.split("/by ");
        String deadlineDescription = deadlineDetails[0].substring(9); // Extracting the task description
        LocalDateTime by = getDateTime(deadlineDetails[1].trim());
        Task newDeadline = new Deadline(deadlineDescription, by, hasDone);
        AddCommand addDeadline = new AddCommand(newDeadline);
        return addDeadline.run(taskList, ui);
    }

    private static String fetchAdd(String userInput, CommandType commandType) throws ShrimpException.MissingArgumentException {
        if (userInput.length() <= 5) {
            throw new ShrimpException.MissingArgumentException(commandType);
        }
        String input = userInput.substring(5);
        Todo newTodo = new Todo(input, hasDone); //creates a new Task.Task object
        AddCommand addTodo = new AddCommand(newTodo);
        return addTodo.run(taskList, ui);
    }

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

    private static String fetchList() throws ShrimpException.EmptyArrayException {
        if (taskList.isEmpty()) {
            throw new ShrimpException.EmptyArrayException();
        }
        ListCommand listCommand = new ListCommand();
        return listCommand.run(taskList, ui);
    }

    private static String fetchExit() {
        ExitCommand exitCommand = new ExitCommand();
        return exitCommand.run(taskList, ui);
    }

    public static void saveTasks() {
        Storage.saveTasks(taskList);
    }

    /**
     * Extracts the task number from the user input for MARK, UNMARK, or DELETE commands.
     *
     * @param userInput The user's input containing the command and task number.
     * @param type      The type of command being processed.
     * @return The task number (zero-based index).
     * @throws ShrimpException If the task number is missing or not a valid integer.
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
     * @param input The date/time string to be parsed.
     * @return The parsed {@code LocalDateTime} object.
     * @throws ShrimpException If the date/time format is invalid.
     */
    static LocalDateTime getDateTime(String input) throws ShrimpException {
        try {
            return LocalDateTime.parse(input, Parser.PATTERN);
        } catch (DateTimeParseException e) {
            throw new ShrimpException.InvalidDateTimeException();
        }
    }
}
