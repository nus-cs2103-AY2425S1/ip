package ScoobyDoo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.InputFormatException;

/**
 * The Parser class provides utility methods for parsing different types of task inputs.
 * It can extract descriptions and dates for events, deadlines, and todo tasks.
 */
public class Parser {
    /**
     * Extracts the description of an event from the input string.
     *
     * @param input The input string user typed.
     * @return The description of the event.
     * @throws InputFormatException If the input format is invalid.
     */
    public static String getEventDescription(String input) throws InputFormatException{
        String[] splitDeadline = input.split(" ", 2);
        if (splitDeadline.length != 2) {
            throw new InputFormatException("Oops! I need a description to save your task");
        }

        String[] splitFromTo = splitDeadline[1].split("/from|/to",3);
        if (splitFromTo.length != 3) {
            throw new InputFormatException("Oops! I need a /from and a /to regex to save your event task");
        }
        return splitFromTo[0].trim();
    }

    /**
     * Parses the /from and /to dates of an event from the input string.
     *
     * @param input The input string containing the event information.
     * @return An array of LocalDateTime objects, where the first element is the /from date and the second is the /to date.
     * @throws InputFormatException If the input format is invalid or the date parsing fails.
     */
    public static LocalDateTime[] getEventFromAndToDate(String input) throws InputFormatException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] splitFromTo = input.split("/from|/to",3);
        if (splitFromTo.length != 3) {
            throw new InputFormatException("Oops! I need a /from and a /to regex to save your event task");
        }
        try {
            LocalDateTime[] fromTo = {LocalDateTime.parse(splitFromTo[1].trim(), formatter),
                    LocalDateTime.parse(splitFromTo[2].trim(),formatter)};
            return fromTo;
        } catch (DateTimeParseException e) {
            throw new InputFormatException("Please input your date in the format of YYYY-MM-DD HH:mm");
        }
    }

    /**
     * Extracts the description of a deadline task from the input string.
     *
     * @param input The input string containing the deadline task information.
     * @return The description of the deadline task.
     * @throws InputFormatException If the input format is invalid.
     */
    public static String getDeadlineDescription(String input) throws InputFormatException{
        String[] splitDeadline = input.split(" ", 2);
        if (splitDeadline.length != 2) {
            throw new InputFormatException("Oops! I need a description to save your task");
        }
        String[] splitBySlash = splitDeadline[1].split("/by",2);
        if (splitBySlash.length != 2) {
            throw new InputFormatException("Oops! I need one /by regex to save your deadline task");
        }
        return splitBySlash[0].trim();
    }

    /**
     * Parses the date of a deadline task from the input string.
     *
     * @param input The input string containing the deadline task information.
     * @return The LocalDateTime object representing the deadline date.
     * @throws InputFormatException If the input format is invalid or the date parsing fails.
     */
    public static LocalDateTime getDeadlineDate(String input) throws InputFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] splitBy = input.split("/by",2);
        if (splitBy.length != 2) {
            throw new InputFormatException("Oops! I need a /by regex to save your deadline task");
        }
        try {
            return LocalDateTime.parse(splitBy[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new InputFormatException("Please input your date in the format of YYYY-MM-DD HH:mm");
        }
    }

    /**
     * Extracts the description of a todo task from the input string.
     *
     * @param input The input string containing the todo task information.
     * @return The description of the todo task.
     * @throws InputFormatException If the input format is invalid.
     */
    public static String getTodoDescription(String input) throws InputFormatException{
        String[] todoSplit = input.split(" ", 2);
        if (todoSplit.length != 2) {
            throw new InputFormatException("Oops!  I need a description to save your task");
        }
        return todoSplit[1].trim();
    }

    public static String getFindTargetWord(String input) throws InputFormatException{
        String[] findSplit = input.split(" ", 2);
        if (findSplit.length != 2) {
            throw new InputFormatException("Please input a word to find!");
        }
        return findSplit[1].trim();
    }
}
