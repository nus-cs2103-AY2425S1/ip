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
     * Command object. It can identify the following:
     * - "bye": Creates a ExitCommand which terminates the program.
     * - "list": Creates a ListCommand to display all tasks in the task list.
     * - "mark": Creates a MarkCommand flagged as mark to mark the task as done.
     * - "unmark": Creates a MarkCommand flagged as unmark to mark the task as not done.
     * - "to-do": Creates an AddCommand which creates a new To-Do task with the information provided by the user.
     * - "deadline": Creates an AddCommand which creates a new Deadline task with the information provided by the user.
     * - "event": Creates an AddCommand which creates a new Event task with the information provided by the user.
     * - "delete": Creates a DeleteCommand which deletes the task.
     * - "find": Creates a FindCommand which matches the tasks based on the keyword provided by the user.
     * - Any other input: Creates a Command which will terminate the program.
     *
     * @param userInput The string input by the user.
     * @return A Command object with respect to the user's input.
     * @throws FishmanException.InvalidCommandException if input does not match any command.
     * @throws FishmanException.MissingArgumentException if command is missing arguments.
     * @throws FishmanException.NumberFormatException if a numeric argument is not a valid number.
     * @throws FishmanException.EmptyListException if trying to mark/unmark a task in an empty task list.
     * @throws FishmanException.IndexOutOfBoundsException if trying to choose a nonexistent item from the list.
     * @throws FishmanException for unexpected errors during parsing.
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

    private static MarkCommand getMarkCommand(TaskList taskList, String[] inputs, boolean isMark) throws FishmanException {
        checkIfTaskListEmpty(taskList);
        int taskIndex = parseTaskIndex(inputs[1], taskList.size());
        return new MarkCommand(taskIndex, isMark);
    }

    private static AddCommand getDeadlineCommand(String[] inputs) throws FishmanException {
        if (!inputs[1].contains("/by")) {
            throw new FishmanException.MissingArgumentException("deadline");
        }
        String[] deadlineString = inputs[1].split("/by");
        LocalDateTime deadlineDate = parseDateTime(deadlineString[1].trim());
        return new AddCommand(new Deadline(deadlineString[0].trim(), false, deadlineDate));
    }

    private static AddCommand getEventCommand(String[] inputs) throws FishmanException {
        if (!inputs[1].contains("/from") || !inputs[1].contains("/to")) {
            throw new FishmanException.MissingArgumentException("event");
        }
        String[] eventString = inputs[1].split("/from|/to");
        LocalDateTime eventStart = parseDateTime(eventString[1].trim());
        LocalDateTime eventEnd = parseDateTime(eventString[2].trim());
        return new AddCommand(new Event(eventString[0].trim(), false, eventStart, eventEnd));
    }

    private static DeleteCommand getDeleteCommand(TaskList taskList, String[] inputs) throws FishmanException {
        int deleteIndex = parseTaskIndex(inputs[1], taskList.size());
        return new DeleteCommand(deleteIndex);
    }

    private static void checkIfTaskListEmpty(TaskList taskList) throws FishmanException {
        if (taskList.isTaskListEmpty()) {
            throw new FishmanException.EmptyListException();
        }
    }

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

    private static LocalDateTime parseDateTime(String dateTimeStr) throws FishmanException {
        assert dateTimeStr != null : "DateTime string should not be null";
        try {
            return LocalDateTime.parse(dateTimeStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new FishmanException.InvalidDateFormatException(dateTimeStr);
        }
    }
}
