package screwllum.utils;

import screwllum.exception.InvalidCommandException;
import screwllum.exception.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides various methods to understand user input, or format input and output in the context of the screwllum bot.
 */
public class Parser {
    /**
     * Parses the user input and breaks it into tokens based on the command.
     * Handles various commands such as "bye", "list", "delete", "toggle", "todo", "deadline", and "event".
     * Depending on the type of command, the respective private handle methods would be called.
     * The first token is always the command in lowercase.
     *
     * @param input The raw input string entered by the user.
     * @return A list of tokens representing the parsed command and its associated details.
     * @throws InvalidCommandException if the command is unrecognized or the input does not follow certain formats.
     * @throws InvalidDateFormatException if a date in the input is not in the expected format.
     */
    public static List<String> parseUserInput(String input) throws InvalidCommandException, InvalidDateFormatException {
        List<String> tokens = new ArrayList<>();
        String[] segments = input.split("/");
        tokens.add(segments[0].split(" ", 2)[0].toLowerCase());
        
        switch (tokens.get(0)) {
        case "bye":
            // Fallthrough
        case "list":
            // Do nothing
            break;
        case "delete":
            // Fallthrough
        case "toggle":
            handleDeleteOrToggle(tokens, segments);
            break;
        case "todo":
            handleToDo(tokens, segments);
            break;
        case "deadline":
            handleDeadline(tokens, segments);
            break;
        case "event":
            handleEvent(tokens, segments);
            break;
        default:
            throw new InvalidCommandException("Pardon me, I did not get what you mean");
        }
        return tokens;
    }

    /**
     * Parses a date string into a LocalDate object via an overloaded method using the default pattern "yyyy-M-d".
     *
     * @param dateString The string representing the date.
     * @return A LocalDate object corresponding to the parsed date string.
     */
    public static LocalDate parseStringToDate(String dateString) {
        return parseStringToDate(dateString, "yyyy-M-d");
    }

    /**
     * Parses a date string into a LocalDate object using the specified date pattern.
     *
     * @param dateString The string representing the date.
     * @param pattern The String pattern to be used for parsing the date string.
     * @return A LocalDate object corresponding to the parsed date string.
     */
    public static LocalDate parseStringToDate(String dateString, String pattern) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Converts a LocalDate object into a string via an overloaded method using the default pattern "yyyy-M-d".
     *
     * @param localDate The LocalDate object to be formatted.
     * @return A string representing the formatted date.
     */
    public static String parseDateToString(LocalDate localDate) {
        return parseDateToString(localDate, "yyyy-M-d");
    }

    /**
     * Converts a LocalDate object into a string using the specified pattern.
     *
     * @param localDate The LocalDate object to be formatted.
     * @param pattern The String pattern to be used for formatting the LocalDate.
     * @return A string representing the formatted date.
     */
    public static String parseDateToString(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }
    
    private static void handleDeleteOrToggle(List<String> tokens, String[] segments) throws InvalidCommandException {
        try {
            String[] firstSegment = segments[0].split(" ", 2);
            int index = Integer.parseInt(firstSegment[1].trim());
            tokens.add(String.valueOf(index));
        } catch (Exception e) {
            throw new InvalidCommandException("The correct usage is: toggle <index:int>, "
                    + "ensure that you inputted a number");
        }
    }
    
    private static void handleToDo(List<String> tokens, String[] segments) throws InvalidCommandException {
        try {
            String[] firstSegment = segments[0].split(" ", 2);
            tokens.add(firstSegment[1].trim());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("The correct usage is: todo <desc:string>, you must have missed out"
                    + " on adding the task description");
        }
    }

    private static void handleDeadline(List<String> tokens, String[] segments)
            throws InvalidCommandException, InvalidDateFormatException {
        try {
            String[] firstSegment = segments[0].split(" ", 2);
            tokens.add(firstSegment[1].trim());
            String[] secondSegment = segments[1].split(" ", 2);
            if (!secondSegment[0].equals("by")) {
                throw new InvalidCommandException("Ensure that you typed /by followed by a space, "
                        + "when inputting your date");
            }
            tokens.add(secondSegment[1].trim());
            parseStringToDate(tokens.get(tokens.size() - 1));
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(tokens.get(tokens.size() - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("The correct usage is: deadline <desc:string> /by <yyyy-M-d>");
        }
    }

    private static void handleEvent(List<String> tokens, String[] segments)
            throws InvalidCommandException, InvalidDateFormatException {
        try {
            String[] firstSegment = segments[0].split(" ", 2);
            tokens.add(firstSegment[1].trim());
            String[] secondSegment = segments[1].split(" ", 2);
            String[] thirdSegment = segments[2].split(" ", 2);
            if (!secondSegment[0].equals("from") || !thirdSegment[0].equals("to")) {
                throw new InvalidCommandException("Ensure that you typed /from <startDate> /to <endDate>");
            }
            tokens.add(secondSegment[1].trim());
            tokens.add(thirdSegment[1].trim());
            parseStringToDate(tokens.get(tokens.size() - 1));
            parseStringToDate(tokens.get(tokens.size() - 2));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("The correct usage is: event <desc:string> /from <yyyy-M-d>"
                    + " /to <yyyy-M-d>");
        } catch (DateTimeParseException e) {
            int size = tokens.size();
            throw new InvalidDateFormatException(tokens.get(size - 1) + " or " + tokens.get(size - 2));
        }
    }
}
