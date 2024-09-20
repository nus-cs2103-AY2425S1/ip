package wenjiebot;

import wenjiebot.commands.*;
import wenjiebot.exceptions.NoFollowUpException;
import wenjiebot.exceptions.UnknownCommandException;

/**
 * The Parser class is responsible for interpreting user input
 * and returning the corresponding Command object for execution in the wenjiebot application.
 */
public class Parser {

    private static String regexToDo = "todo .*";
    private static String regexDeadline = "deadline .* /by .*";
    private static String regexEvent = "event .* /.* /.*";

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user's input as a String.
     * @return The corresponding Command object based on the user's input.
     * @throws UnknownCommandException If the command is not recognized.
     */
    public static Command parse(String input) throws UnknownCommandException, NoFollowUpException {
        String[] parts = input.split(" ");
        String action = parts[0].toLowerCase();

        switch (action) {
        case "mark":
            return new MarkCommand(false, input);
            // Fallthrough
        case "unmark":
            return new UnmarkCommand(false, input);
            // Fallthrough
        case "delete":
            return new DeleteCommand(false, input);
            // Fallthrough
        case "bye":
            return new ByeCommand(true, input);
            // Fallthrough
        case "find":
            return new FindCommand(false, input);
            // Fallthrough
        case "list":
            return new ListCommand(false, input);
            // Fallthrough
        case "snooze":
            return new SnoozeCommand(false, input);
            // Fallthrough
        case "todo":
            if (input.matches(regexToDo)) {
                return new AddCommand(false, input, AddCommand.TypeOfEvent.TODO);
            } else {
                throw new NoFollowUpException();
            }
            // Fallthrough
        case "event":
            if (input.matches(regexEvent)) {
                return new AddCommand(false, input, AddCommand.TypeOfEvent.EVENT);
            } else {
                throw new NoFollowUpException();
            }
            // Fallthrough
        case "deadline":
            if (input.matches(regexDeadline)) {
                return new AddCommand(false, input, AddCommand.TypeOfEvent.DEADLINE);
            } else {
                throw new NoFollowUpException();
            }
            // Fallthrough
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the 'from' date from the input string.
     *
     * @param input the input string from the user.
     * @return the 'from' date as a string.
     */
    public static String parseFromDate(String input) {
        int fromIndex = input.indexOf("/from");

        if (fromIndex == -1) {
            return "";
        }

        String fromPart = input.substring(fromIndex + 6).trim();
        int toIndex = fromPart.indexOf("/to");

        if (toIndex == -1) {
            return "";
        }

        fromPart = fromPart.substring(0, toIndex).trim();
        assert (!fromPart.isEmpty());
        return fromPart;
    }

    /**
     * Parses the 'to' date from the input string.
     *
     * @param input the input string from the user.
     * @return the 'to' date as a string.
     */
    public static String parseToDate(String input) {
        int toIndex = input.indexOf("/to");
        if (toIndex == -1) {
            return "";
        }
        String toPart = input.substring(toIndex + 4).trim();
        assert (!toPart.isEmpty());
        return toPart;
    }


    /**
     * Parses the 'by' date from the input string.
     *
     * @param input the user inputted string.
     * @return the 'by' date as a string.
     */
    public static String parseByDate(String input) {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            return "";
        }
        String byPart = input.substring(byIndex + 4).trim();
        assert (!byPart.isEmpty());
        return byPart;
    }
}
