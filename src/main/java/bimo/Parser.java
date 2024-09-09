package bimo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import bimo.command.AddCommand;
import bimo.command.ByeCommand;
import bimo.command.Command;
import bimo.command.CommandType;
import bimo.command.DeleteCommand;
import bimo.command.FindCommand;
import bimo.command.HelpCommand;
import bimo.command.ListCommand;
import bimo.command.MarkCommand;
import bimo.command.UnmarkCommand;
import bimo.exception.BimoException;
import bimo.exception.InvalidDateFormatException;
import bimo.exception.InvalidTaskNumberException;
import bimo.exception.MissingDescriptionException;
import bimo.tasks.Deadline;
import bimo.tasks.Event;
import bimo.tasks.Task;
import bimo.tasks.ToDo;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Converts user input to the appropriate Command object.
     *
     * @param input Input that user typed in.
     * @return Command object.
     * @throws BimoException If there is an invalid command or invalid date.
     */
    public static Command parse(String input) throws BimoException {
        String[] parsedArray = input.split(" ");
        String userCommand = parsedArray[0].toUpperCase();
        CommandType command = getCommand(userCommand);
        switch (command) {
        case LIST:
            return new ListCommand();
        case MARK:
            int indexToMark = parseIndex(parsedArray);
            return new MarkCommand(indexToMark);
        case UNMARK:
            int indexToUnmark = parseIndex(parsedArray);
            return new UnmarkCommand(indexToUnmark);
        case TODO:
            String toDodescription = parseDescription(input);
            return new AddCommand(new ToDo(toDodescription));
        case EVENT:
            Task eventTask = createEventTask(input);
            return new AddCommand(eventTask);
        case DEADLINE:
            Task deadlineTask = createDeadlineTask(input);
            return new AddCommand(deadlineTask);
        case DELETE:
            int indexToDelete = parseIndex(parsedArray);
            return new DeleteCommand(indexToDelete);
        case FIND:
            String[] words = input.split(" ");
            return new FindCommand(words);
        case BYE:
            return new ByeCommand();
        default:
            return new HelpCommand();
        }
    }

    /**
     * Creates the corresponding enum type for user commands.
     *
     * @param input Command user entered.
     * @return Type of command.
     */
    public static CommandType getCommand(String input) {
        try {
            return CommandType.valueOf(input);
        } catch (IllegalArgumentException e) {
            return CommandType.HELP;
        }
    }

    /**
     * Returns description portion of user input.
     *
     * @param input Input typed in by user.
     * @return String value of task description.
     * @throws BimoException If there is no description typed in.
     */
    public static String parseDescription(String input) throws MissingDescriptionException {
        String[] parsedArray = input.split(" ");
        if (parsedArray.length <= 1) {
            throw new MissingDescriptionException();
        }
        parsedArray[0] = "";
        return removeSpace(parsedArray);
    }

    /**
     * Returns index of specified task in list.
     *
     * @param parsedArray Array of words specified by user.
     * @return The index of target task in list.
     * @throws InvalidTaskNumberException Thrown if user does not provide a task number.
     */
    public static int parseIndex(String[] parsedArray) throws InvalidTaskNumberException {
        if (parsedArray.length <= 1) {
            throw new InvalidTaskNumberException();
        }
        String inputNumber = parsedArray[1];
        try {
            int index = Integer.parseInt(inputNumber) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Removes spacing in front of String value of task description.
     *
     * @param input Array of words inside description separated by " ".
     * @return String value of description with empty space removed.
     */
    public static String removeSpace(String[] input) {
        String [] temp = new String[input.length - 1];
        for (int i = 1; i < input.length; i++) {
            temp[i - 1] = input[i];
        }
        return String.join(" ", temp);
    }

    /**
     * Retrieves date from user input in the form yyyy-mm-dd.
     *
     * @param isDeadline Determines if there is two dates.
     * @param isEnd Determines if an end date is needed.
     * @param array Array of Strings containing date.
     * @return Date in the format yyyy-mm-dd.
     * @throws BimoException If no date is provided.
     */
    public static String parseDate(boolean isDeadline, boolean isEnd,
            String[] array) throws BimoException {
        if (array.length <= 1) {
            String type = isDeadline ? " /by" : " /from .... /to";
            throw new BimoException("Please provide a date using" + type);
        } else if (!isDeadline) {
            array = array[1].split(" /to ");
            if (array.length <= 1) {
                throw new BimoException("Please provide a deadline using /to");
            }
        }
        return isEnd ? array[1] : array[0];
    }

    /**
     * Converts a String date input to a LocalDate object.
     *
     * @param date Date user keys in.
     * @return LocalDate instance.
     * @throws InvalidDateFormatException Thrown if date input is not given in yyyy-mm-dd.
     */
    public static LocalDate convertDate(String date) throws InvalidDateFormatException {
        try {
            LocalDate dateObject = LocalDate.parse(date);
            return dateObject;
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Creates a Event task with start and due date.
     *
     * @param input User input String.
     * @return EventTask object.
     * @throws BimoException if no error.
     */
    public static Task createEventTask(String input) throws BimoException {
        String[] array = input.split("/from ");
        String eventDescription = parseDescription(array[0]);
        String startDate = parseDate(false, false, array);
        String endDate = parseDate(false, true, array);
        LocalDate startDateObject = convertDate(startDate);
        LocalDate endDateObject = convertDate(endDate);
        Task eventTask = new Event(eventDescription, startDateObject,
                endDateObject);
        return eventTask;
    }

    /**
     * Creates a Event task with start and due date.
     *
     * @param input User input String.
     * @return Deadline task object
     * @throws BimoException
     */
    public static Task createDeadlineTask(String input) throws BimoException {
        String[] arrayString = input.split("/by ");
        String description = parseDescription(arrayString[0]);
        String dueDate = parseDate(true, true, arrayString);
        LocalDate dueDateObject = convertDate(dueDate);
        Task deadlineTask = new Deadline(description, dueDateObject);
        return deadlineTask;
    }
}
