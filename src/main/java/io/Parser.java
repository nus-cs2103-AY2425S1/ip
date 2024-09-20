package io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import commands.AddTaskCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.InvalidDateException;
import exceptions.InvalidTaskException;

/**
 * The {@code Parser} class is responsible for interpreting user input and converting it into
 * the corresponding {@link commands.Command} objects that can be executed.
 * It also provides functionality to convert stored task data into a format that can be
 * processed by the system, and vice versa.
 * <p>
 * The class supports various commands including adding tasks, marking tasks as done or undone,
 * deleting tasks, finding tasks by description or tags, and listing all tasks.
 * </p>
 * <p>
 * The {@code Parser} ensures that user input is correctly parsed and that dates are converted
 * to the expected formats. It also ensures that task data stored in files can be converted back
 * to user input formats when loading tasks from storage.
 * </p>
 * <p>
 * If invalid input is encountered, the {@code Parser} throws relevant exceptions such as
 * {@link exceptions.InvalidTaskException} and {@link exceptions.InvalidDateException}.
 * </p>
 */
public class Parser {

    /**
     * Converts stored data input into a user input format that can be processed by the system.
     * The data input format is typically used for loading tasks from storage, and this method converts it into
     * a format that matches user input, including task type and associated details.
     *
     * @param data The string containing the stored task data in a specific format.
     * @return The string representing the user input format based on the stored data.
     */
    public static String dataInputToUserInput(String data) throws InvalidDateException {
        assert !data.isEmpty() : "You should not be recording empty strings, check your data!!";
        data = data.substring(data.indexOf("["));
        char taskType = data.charAt(1);
        int descriptionStartIndex = data.indexOf("] ", data.indexOf("]") + 1) + 2;
        String description;
        switch (taskType) {
        case 'T':
            return todoInputToUserInput(data, descriptionStartIndex);
        case 'E':
            return eventInputToUserInput(data, descriptionStartIndex);
        case 'D':
            return deadlineInputToUserInput(data, descriptionStartIndex);
        default:
            // default should never be reached, data coming from persistent input always has correct format
            return "";
        }
    }

    private static String todoInputToUserInput(String data, int descriptionStartIndex) {
        return "todo " + data.substring(descriptionStartIndex);
    }


    private static String eventInputToUserInput(String data, int descriptionStartIndex) throws InvalidDateException {
        int endIndex = data.indexOf("(");
        int fromIndex = data.indexOf("(from");
        int toIndex = data.indexOf("to:");
        int lastIndex = data.indexOf(")");

        String description = data.substring(descriptionStartIndex, endIndex).trim();
        String fromDate = data.substring(fromIndex + 6, toIndex - 1);
        fromDate = Parser.convertDateFormat(fromDate, "MMM dd yyyy", "dd/MM/yyyy");
        String toDate = data.substring(toIndex + 4, lastIndex);
        toDate = Parser.convertDateFormat(toDate, "MMM dd yyyy", "dd/MM/yyyy");
        return "event " + description + " /from " + fromDate + " /to " + toDate;
    }

    private static String deadlineInputToUserInput(String data, int descriptionStartIndex) throws InvalidDateException {
        int descriptionEndIndex = data.indexOf("(");
        int deadlineIndex = data.indexOf("(by");

        String description = data.substring(descriptionStartIndex, descriptionEndIndex).trim();
        String date = data.substring(deadlineIndex + 4, data.indexOf(")"));
        String inputDateFormat = Parser.convertDateFormat(date.trim(), "MMM dd yyyy", "dd/MM/yyyy");
        return "deadline " + description + " /by " + inputDateFormat;
    }

    private static String convertDateFormat(String dateStr, String sourceFormat, String resultFormat)
            throws InvalidDateException {
        try {
            SimpleDateFormat sourceDateFormat = new SimpleDateFormat(sourceFormat);
            Date date = sourceDateFormat.parse(dateStr);
            SimpleDateFormat resultDateFormat = new SimpleDateFormat(resultFormat);
            return resultDateFormat.format(date);
        } catch (ParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Converts user input into a corresponding command object that can be executed.
     * This method interprets the user's input string and determines the appropriate command
     * to return, based on the input. The method supports various commands including "bye", "list",
     * "delete", "mark", "unmark", and task creation commands ("todo", "deadline", "event").
     *
     * @param userInput The string containing the user's command input.
     * @return A Command object representing the user's intended action.
     * @throws InvalidTaskException If the user input does not match any valid command or task type.
     * @throws ArrayIndexOutOfBoundsException If the user input is missing required arguments.
     */
    public static Command inputToCommand(String userInput) throws InvalidTaskException,
            ArrayIndexOutOfBoundsException {
        String strippedInput = userInput.toLowerCase().trim();

        String[] words = strippedInput.split(" ");
        switch (words[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(Integer.parseInt(words[1]) - 1);
        case "mark":
            return new MarkCommand(Integer.parseInt(words[1]) - 1);
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
        case "find":
            return FindCommand.createFindCommand(userInput);
        case "todo", "deadline", "event":
            return new AddTaskCommand(userInput);
        default:
            throw new InvalidTaskException();
        }
    }
}
















