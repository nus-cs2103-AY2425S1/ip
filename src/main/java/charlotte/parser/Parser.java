package charlotte.parser;

import charlotte.command.AddCommand;
import charlotte.command.Command;
import charlotte.command.DeleteCommand;
import charlotte.command.ExitCommand;
import charlotte.command.FindCommand;
import charlotte.command.ListCommand;
import charlotte.command.MarkCommand;
import charlotte.command.RemindersCommand;
import charlotte.command.UnmarkCommand;
import charlotte.exception.CharlotteException;
import charlotte.task.Deadline;
import charlotte.task.Event;
import charlotte.task.Task;
import charlotte.task.ToDo;

/**
 * The Parser class is responsible for interpreting user input
 * and returning the appropriate command to be executed.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput The input string entered by the user.
     * @return The Command object corresponding to the user's input.
     * @throws CharlotteException If the user input is not recognized or formatted incorrectly.
     */
    public static Command parse(String userInput) throws CharlotteException {
        String[] inputParts = userInput.split(" ", 2);
        String command = inputParts[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
        case "delete":
            return handleSimpleCommand(command, inputParts);
        case "todo":
            return handleToDoCommand(inputParts);
        case "deadline":
            return handleDeadlineCommand(inputParts);
        case "event":
            return handleEventCommand(inputParts);
        case "find":
            return handleFindCommand(inputParts);
        case "reminders":
            return new RemindersCommand();
        case "bye":
            return new ExitCommand();
        default:
            throw new CharlotteException("Oh no, I don't know what that means :( Try again!");
        }
    }

    /**
     * Handles the parsing of simple commands that require a task number, such as "mark", "unmark", and "delete".
     *
     * @param command The specific command (mark, unmark, or delete).
     * @param inputParts The split parts of the user input.
     * @return The corresponding Command object for the given command.
     * @throws CharlotteException If the task number is not provided or invalid.
     */
    private static Command handleSimpleCommand(String command, String[] inputParts) throws CharlotteException {
        if (inputParts.length < 2) {
            throw new CharlotteException("Oops! You need to specify the task number!");
        }
        int taskNumber = Integer.parseInt(inputParts[1]);
        switch (command) {
        case "mark":
            return new MarkCommand(taskNumber);
        case "unmark":
            return new UnmarkCommand(taskNumber);
        case "delete":
            return new DeleteCommand(taskNumber);
        default:
            throw new CharlotteException("Unknown command: " + command);
        }
    }

    /**
     * Handles the parsing of the "todo" command to create a new ToDo task.
     *
     * @param inputParts The split parts of the user input.
     * @return An AddCommand object that adds a new ToDo task.
     * @throws CharlotteException If the description for the todo task is empty.
     */
    private static Command handleToDoCommand(String[] inputParts) throws CharlotteException {
        if (inputParts.length < 2) {
            throw new CharlotteException("Oops! The description of a todo cannot be empty!");
        }
        Task todoTask = new ToDo(inputParts[1]);
        return new AddCommand(todoTask);
    }


    /**
     * Handles the parsing of the "deadline" command to create a new Deadline task.
     *
     * @param inputParts The split parts of the user input.
     * @return An AddCommand object that adds a new Deadline task.
     * @throws CharlotteException If the deadline format is incorrect.
     */
    private static Command handleDeadlineCommand(String[] inputParts) throws CharlotteException {
        if (inputParts.length < 2 || !inputParts[1].contains(" /by ")) {
            throw new CharlotteException("Oops! The correct format for deadline is: deadline task /by date");
        }
        String[] deadlineParts = inputParts[1].split(" /by ");
        Task deadlineTask = new Deadline(deadlineParts[0], deadlineParts[1]);
        return new AddCommand(deadlineTask);
    }

    /**
     * Handles the parsing of the "event" command to create a new Event task.
     *
     * @param inputParts The split parts of the user input.
     * @return An AddCommand object that adds a new Event task.
     * @throws CharlotteException If the event format is incorrect.
     */
    private static Command handleEventCommand(String[] inputParts) throws CharlotteException {
        if (inputParts.length < 2 || !inputParts[1].contains(" /from ") || !inputParts[1].contains(" /to ")) {
            throw new CharlotteException("Oops! The correct format for event is: event task /from start /to end");
        }
        String[] eventParts = inputParts[1].split(" /from | /to ");
        Task eventTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
        return new AddCommand(eventTask);
    }

    /**
     * Handles the parsing of the "find" command to search for tasks based on a keyword.
     *
     * @param inputParts The split parts of the user input.
     * @return A FindCommand object that finds tasks matching the specified keyword.
     * @throws CharlotteException If the keyword for finding tasks is not provided.
     */
    private static Command handleFindCommand(String[] inputParts) throws CharlotteException {
        if (inputParts.length < 2) {
            throw new CharlotteException("Oops! You need to specify a keyword to find!");
        }
        return new FindCommand(inputParts[1]);
    }
}
