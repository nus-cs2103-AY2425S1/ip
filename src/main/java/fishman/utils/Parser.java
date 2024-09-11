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
import fishman.command.UpdateCommand;
import fishman.exception.FishmanException;
import fishman.task.Deadline;
import fishman.task.Event;
import fishman.task.Task;
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
    public static Command parse(String userInput, TaskList tasks) throws FishmanException {
        assert tasks != null : "TaskList should not be null";

        if (userInput == null || userInput.trim().isEmpty()) {
            throw new FishmanException.InvalidCommandException();
        }
        String[] inputs = userInput.split(" ", 2);
        String commandPhrase = inputs[0].toLowerCase();

        if (inputs.length == 1 && commandPhrase.matches("mark|unmark|todo|deadline|event|delete|find")) {
            throw new FishmanException.MissingArgumentException(commandPhrase);
        }

        try {
            switch (commandPhrase) {
            case "bye":
                return new ExitCommand();
            case "list":
                if (tasks.isEmpty()) {
                    throw new FishmanException.EmptyListException();
                }
                return new ListCommand();
            case "mark":
                if (tasks.isEmpty()) {
                    throw new FishmanException.EmptyListException();
                }
                int markIndex = Integer.parseInt(inputs[1]) - 1;
                if (markIndex < 0 || markIndex >= tasks.size()) {
                    throw new FishmanException.IndexOutOfBoundsException(markIndex + 1);
                }
                return new MarkCommand(markIndex, true);
            case "unmark":
                if (tasks.isEmpty()) {
                    throw new FishmanException.EmptyListException();
                }
                int unmarkIndex = Integer.parseInt(inputs[1]) - 1;
                if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
                    throw new FishmanException.IndexOutOfBoundsException(unmarkIndex + 1);
                }
                return new MarkCommand(unmarkIndex, false);
            case "todo":
                return new AddCommand(new ToDo(inputs[1], false));
            case "deadline":
                if (!inputs[1].contains("/by")) {
                    throw new FishmanException.MissingArgumentException("deadline");
                }
                String[] deadlineString = inputs[1].split("/by");
                LocalDateTime deadlineDate = parseDateTime(deadlineString[1].trim());
                return new AddCommand(new Deadline(deadlineString[0].trim(), false, deadlineDate));
            case "event":
                if (!inputs[1].contains("/from") || !inputs[1].contains("/to")) {
                    throw new FishmanException.MissingArgumentException("event");
                }
                String[] eventString = inputs[1].split("/from|/to");
                LocalDateTime fromDate = parseDateTime(eventString[1].trim());
                LocalDateTime toDate = parseDateTime(eventString[2].trim());
                return new AddCommand(new Event(eventString[0].trim(), false, fromDate, toDate));
            case "delete":
                int deleteIndex = Integer.parseInt(inputs[1]) - 1;
                if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
                    throw new FishmanException.IndexOutOfBoundsException(deleteIndex + 1);
                }
                return new DeleteCommand(deleteIndex);
            case "find":
                return new FindCommand(inputs[1]);
            case "update":
                return getUpdateCommand(inputs, tasks);
            default:
                throw new FishmanException.InvalidCommandException();
            }
        } catch (NumberFormatException e) {
            throw new FishmanException.NumberFormatException(e.getMessage());
        }
    }


    private static UpdateCommand getUpdateCommand(String[] inputs, TaskList tasks) throws FishmanException {
        validateUpdateInput(inputs);

        int updateIndex = parseUpdateIndex(inputs[1]);
        Task taskToUpdate = tasks.getTask(updateIndex);

        String inputArgs = inputs[1].substring(inputs[1].indexOf(' ') + 1).trim();

        LocalDateTime newDateTimeStart;
        LocalDateTime newDateTimeEnd = null;

        if (taskToUpdate instanceof Deadline) {
            newDateTimeStart = parseDeadlineInput(inputArgs);
        } else if (taskToUpdate instanceof Event) {
            LocalDateTime[] eventDates = parseEventInput(inputArgs);
            newDateTimeStart = eventDates[0];
            newDateTimeEnd = eventDates[1];
        } else {
            throw new FishmanException.InvalidUpdateTypeException();
        }

        return new UpdateCommand(updateIndex, newDateTimeStart, newDateTimeEnd);
    }

    private static LocalDateTime parseDeadlineInput(String inputArgs) throws FishmanException {
        if (!inputArgs.matches("^/by\\s+.+$")) {
            throw new FishmanException.MissingArgumentException("updateDeadline");
        }

        String[] updateDetails = inputArgs.split("/by", 2);
        return parseDateTime(updateDetails[1].trim());
    }

    private static LocalDateTime[] parseEventInput(String inputArgs) throws FishmanException {
        if (!inputArgs.matches("^/from\\s+.+\\s+/to\\s+.+$")) {
            throw new FishmanException.MissingArgumentException("Event tasks must have the format: update index /from date /to date.");
        }

        String[] updateDetails = inputArgs.split("\\s*/from\\s*|\\s*/to\\s*", 3);
        LocalDateTime newDateTimeStart = parseDateTime(updateDetails[1].trim());
        LocalDateTime newDateTimeEnd = parseDateTime(updateDetails[2].trim());

        return new LocalDateTime[]{newDateTimeStart, newDateTimeEnd};
    }

    private static void validateUpdateInput(String[] inputs) throws FishmanException {
        if (inputs.length < 2) {
            throw new FishmanException.MissingArgumentException("update");
        }
    }

    private static int parseUpdateIndex(String input) throws FishmanException {
        try {
            return Integer.parseInt(input.split(" ")[0].trim()) - 1;
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
