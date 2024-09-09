package nuffle.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nuffle.command.*;
import nuffle.exception.NuffleException;

/**
 * The Parser class is responsible for interpreting user input and generating
 * the appropriate command to execute. It validates specific input formats and translates
 * commands into executable actions within the task management system.
 */
public class Parser {
    private static final String DATE_TIME_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
    // interpret user commands and create the task objects


    /**
     * Validates whether the input date and time string follows the required format (yyyy-MM-dd HHmm).
     *
     * @param dateTime The input date and time string to validate.
     * @return True if the input follows the required format, false otherwise.
     */
    public static boolean isValidateDateTimeFormat(String dateTime) {
        Pattern pattern = Pattern.compile(DATE_TIME_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(dateTime);
        return matcher.matches();
    }

    /**
     * Parses the user's input string and returns the appropriate Command object
     * based on the specified command. The method checks the input against different
     * commands (e.g., "bye", "list", "mark", "unmark", "todo", "deadline", "event", "find").
     *
     * @param userInput The user's input string containing the command.
     * @return The corresponding {@code Command} object to execute the user's command.
     * @throws NuffleException If the user's input does not match any known command.
     */
    public static Command parseCommand(String userInput) throws NuffleException {
        if (userInput.equals("bye")) {
            // Program will exit
            return new ByeCommand(userInput);

        } else if (userInput.equals("list")) {
            // Program will list all the tasks that was input by the user
            return new ListCommand(userInput);

        } else if (userInput.startsWith("mark")) {
            // Program will mark the specified task based on the index provided
            return new MarkCommand(userInput);

        } else if (userInput.startsWith("unmark")) {
            // Program will unmark the specified task based on the index provided
            return new UnmarkCommand(userInput);

        } else if (userInput.startsWith("delete")) {
            // Program will delete the specified task based on the index provided
            return new DeleteCommand(userInput);

        } else if (userInput.startsWith("todo")) {
            // Program will add a todo task to the list
            return new TodoCommand(userInput);

        } else if (userInput.startsWith("deadline")) {
            // Program will add a deadline task to the list
            return new DeadlineCommand(userInput);

        } else if (userInput.startsWith("event")) {
            // Program will add an event task to the list
            return new EventCommand(userInput);

        } else if (userInput.startsWith("find")) {
            // get the description and parse as argument to the find function
            return new FindCommand(userInput);
        } else {
            throw NuffleException.unknown();
        }

    }


}
