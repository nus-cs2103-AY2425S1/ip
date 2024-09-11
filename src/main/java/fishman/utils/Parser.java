package fishman.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import fishman.command.AddCommand;
import fishman.command.Command;
import fishman.command.DeleteCommand;
import fishman.command.ExitCommand;
import fishman.command.FindCommand;
import fishman.command.ListCommand;
import fishman.command.MarkCommand;
import fishman.exception.FishmanException;
import fishman.task.Deadline;
import fishman.task.Event;
import fishman.task.TaskList;
import fishman.task.ToDo;


/**
 * The Parser class is used to interpret user input and create the appropriate
 * Command objects.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses the user input and returns the appropriate Command object.
     * This method interprets the user input, splitting the input to determine the command
     * and respective index and creates the appropriate
     * Command object.
     *
     * @param userInput The string input by the user.
     * @return A Command object with respect to the user's input.
     * @throws FishmanException.InvalidCommandException if input does not match any command.
     * @throws FishmanException.MissingArgumentException if command is missing arguments.
     */
    public static Command parse(String userInput, TaskList taskList) throws FishmanException {
        assert taskList != null : "TaskList should not be null";

        if (userInput == null || userInput.trim().isEmpty()) {
            throw new FishmanException.InvalidCommandException();
        }

        String[] inputs = userInput.split(" ", 2);
        String commandPhrase = inputs[0].toLowerCase();

        if (inputs.length == 1 && commandPhrase.matches("mark|unmark|todo|deadline|event|delete|find")) {
            throw new FishmanException.MissingArgumentException(commandPhrase);
        }

        switch (commandPhrase) {
        case "bye":
            return new ExitCommand();
        case "list":
            checkIfTaskListEmpty(taskList);
            return new ListCommand();
        case "mark":
            return getMarkCommand(taskList, inputs, true);
        case "unmark":
            return getMarkCommand(taskList, inputs, false);
        case "todo":
            return new AddCommand(new ToDo(inputs[1], false));
        case "deadline":
            return getDeadlineCommand(inputs);
        case "event":
            return getEventCommand(inputs);
        case "delete":
            return getDeleteCommand(taskList, inputs);
        case "find":
            return new FindCommand(inputs[1]);
        default:
            throw new FishmanException.InvalidCommandException();
        }
    }

    /**
     * Creates a MarkCommand based on user's input to mark or unmark a task.
     *
     * @param taskList The TaskList containing the tasks.
     * @param inputs The user input containing the command and task index.
     * @param isMark A boolean indicating whether to mark or unmark the task.
     * @return A MarkCommand object for the specified task.
     * @throws FishmanException if the task list is empty or the task index is invalid.
     */
    private static MarkCommand getMarkCommand(TaskList taskList, String[] inputs, boolean isMark)
            throws FishmanException {
        checkIfTaskListEmpty(taskList);
        int taskIndex = parseTaskIndex(inputs[1], taskList.size());
        return new MarkCommand(taskIndex, isMark);
    }

    /**
     * Creates an AddCommand for a Deadline task based on user's input.
     *
     * @param inputs The user input containing the command and task details.
     * @return An AddCommand object for the Deadline task.
     * @throws FishmanException if the command is missing arguments.
     */
    private static AddCommand getDeadlineCommand(String[] inputs) throws FishmanException {
        if (!inputs[1].contains("/by")) {
            throw new FishmanException.MissingArgumentException("deadline");
        }
        String[] deadlineString = inputs[1].split("/by");
        LocalDateTime deadlineDate = parseDateTime(deadlineString[1].trim());
        return new AddCommand(new Deadline(deadlineString[0].trim(), false, deadlineDate));
    }

    /**
     * Creates an AddCommand for an Event task based on user's input.
     *
     * @param inputs The user input containing the command and task details.
     * @return An AddCommand object for the Event task.
     * @throws FishmanException if the command is missing arguments.
     */
    private static AddCommand getEventCommand(String[] inputs) throws FishmanException {
        if (!inputs[1].contains("/from") || !inputs[1].contains("/to")) {
            throw new FishmanException.MissingArgumentException("event");
        }
        String[] eventString = inputs[1].split("/from|/to");
        LocalDateTime eventStart = parseDateTime(eventString[1].trim());
        LocalDateTime eventEnd = parseDateTime(eventString[2].trim());
        return new AddCommand(new Event(eventString[0].trim(), false, eventStart, eventEnd));
    }

    /**
     * Creates a DeleteCommand based on user's input to delete a task.
     *
     * @param taskList The Task List containing the current tasks.
     * @param inputs The user input containing the command and task index.
     * @return A DeleteCommand object for the specified task.
     * @throws FishmanException if the task index is invalid.
     */
    private static DeleteCommand getDeleteCommand(TaskList taskList, String[] inputs) throws FishmanException {
        int deleteIndex = parseTaskIndex(inputs[1], taskList.size());
        return new DeleteCommand(deleteIndex);
    }

    /**
     * Checks if the task list is empty and throws an exception if it is empty.
     *
     * @param taskList The TaskList to check.
     * @throws FishmanException.EmptyListException if the task list is empty.
     */
    private static void checkIfTaskListEmpty(TaskList taskList) throws FishmanException {
        if (taskList.isTaskListEmpty()) {
            throw new FishmanException.EmptyListException();
        }
    }

    /**
     * Parses the task index from user's input and validates it.
     *
     * @param indexStr The string representation of the task index.
     * @param size The size of the task list.
     * @return The index of the task.
     * @throws FishmanException.NumberFormatException if the index is not a valid number.
     * @throws FishmanException.IndexOutOfBoundsException if the index is out of bounds.
     */
    private static int parseTaskIndex(String indexStr, int size) throws FishmanException {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index < 0 || index >= size) {
                throw new FishmanException.IndexOutOfBoundsException(index + 1);
            }
            return index;
        } catch (NumberFormatException e) {
            throw new FishmanException.NumberFormatException(e.getMessage());
        }
    }

    /**
     * Parses a date-time string into a LocalDateTime object using the predefined format.
     *
     * @param dateTimeStr The date time string to parse.
     * @return The parsed LocalDateTime object.
     * @throws FishmanException.InvalidDateFormatException if the date-time string is not in the expected format.
     */
    private static LocalDateTime parseDateTime(String dateTimeStr) throws FishmanException {
        assert dateTimeStr != null : "DateTime string should not be null";
        try {
            return LocalDateTime.parse(dateTimeStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new FishmanException.InvalidDateFormatException(dateTimeStr);
        }
    }
}
