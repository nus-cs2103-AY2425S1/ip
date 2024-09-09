package bimo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import bimo.command.AddCommand;
import bimo.command.ByeCommand;
import bimo.command.Command;
import bimo.command.DeleteCommand;
import bimo.command.FindCommand;
import bimo.command.ListCommand;
import bimo.command.MarkCommand;
import bimo.command.UnmarkCommand;
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
        String cmd = parsedArray[0].toLowerCase();
        if (cmd.equals("list")) {
            return new ListCommand();

        } else if (cmd.equals("mark")) {
            int index = Integer.valueOf(parsedArray[1]) - 1;
            return new MarkCommand(index);

        } else if (cmd.equals("unmark")) {
            int index = Integer.valueOf(parsedArray[1]) - 1;
            return new UnmarkCommand(index);

        } else if (cmd.equals("todo")) {
            String description = parseDescription(input);
            return new AddCommand(new ToDo(description));

        } else if (cmd.equals("event")) {
            String[] array = input.split("/from ");
            String description = parseDescription(array[0]);
            String start = parseDate(false, false, array);
            String end = parseDate(false, true, array);
            LocalDate startDate = convertDate(start);
            LocalDate endDate = convertDate(end);
            Task task = new Event(description, startDate,
                   endDate);
            return new AddCommand(task);

        } else if (cmd.equals("deadline")) {
            String[] array = input.split("/by ");
            String description = parseDescription(array[0]);
            String dueDate = parseDate(true, true, array);
            LocalDate ld = convertDate(dueDate);
            Task task = new Deadline(description, ld);
            return new AddCommand(task);

        } else if (cmd.equals("delete")) {
            int index = Integer.valueOf(parsedArray[1]) - 1;
            return new DeleteCommand(index);

        } else if (cmd.equals("bye")) {
            return new ByeCommand();
        } else if (cmd.equals("find")) {
            String[] words = input.split(" ");
            return new FindCommand(words);

        } else {
            throw new BimoException("Sorry, I do not understand you \n"
                    + "as this is not a valid command");
        }
    }

    /**
     * Returns description portion of user input.
     *
     * @param input Input typed in by user.
     * @return String value of task description.
     * @throws BimoException If there is no description typed in.
     */
    public static String parseDescription(String input) throws BimoException {
        String[] parsedArray = input.split(" ");
        if (parsedArray.length <= 1) {
            throw new BimoException("Please key in description for your task");
        }
        assert parsedArray.length > 1 : "Description should be entered";
        parsedArray[0] = "";
        return removeSpace(parsedArray);
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
     * @throws BimoException If invalid date input is given.
     */
    public static LocalDate convertDate(String date) throws BimoException {
        try {
            LocalDate ld = LocalDate.parse(date);
            return ld;
        } catch (DateTimeParseException e) {
            throw new BimoException("Unable to parse date, please use yyyy-mm-dd");
        }
    }
}
