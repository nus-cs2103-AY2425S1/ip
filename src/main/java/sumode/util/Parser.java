package sumode.util;

import sumode.exception.WrongSyntaxForCommandException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * In charge of parsing jobs in SumoDE.
 * <p>
 * All methods are static so no initialisation required.
 */
public class Parser {

    /**
     * Returns an array of String of size 2 with first String being command and
     * second String being additional details following the command.
     * <p>
     * If no additional details are given, the second String will be an empty String.
     *
     * @param input a line of user input to chatbot
     * @return a String array of size 2
     */
    public static String[] splitCommandAndAction(String input) {
        int spaceLocation = input.indexOf(" ");
        String commandString;
        String item;

        if (spaceLocation == -1) {
            commandString = input;
            item = "";
        } else {
            commandString = input.substring(0, spaceLocation);
            item = input.substring(spaceLocation + 1);
        }
        return new String[] {commandString, item};
    }

    /**
     * Returns an array of String of size 2 with first String being task details and
     * second String being deadline to meet
     * <p>
     * Second String may of may not be yyyy-mm-dd format
     * @param item input of user that come after command
     * @return a String array of size 2
     * @throws WrongSyntaxForCommandException thrown when Wrong Syntax for command is given
     */
    public static String[] parseDeadline(String item) throws WrongSyntaxForCommandException {
        int spaceLocation = item.indexOf(" /by ");
        if (spaceLocation == -1) {
            throw new WrongSyntaxForCommandException(Command.DEADLINE);
        }
        String name = item.substring(0, spaceLocation).trim();
        String due = item.substring(spaceLocation + 5).trim();

        if (name.isEmpty() | due.isEmpty()) {
            throw new WrongSyntaxForCommandException(Command.DEADLINE);
        }
        return new String[] {name, due};
    }

    /**
     * Returns an array of String of size 3 with first String being task details,
     * second String being start date and
     * third String being end date
     * <p>
     * Second/Third String may of may not be yyyy-mm-dd format
     * @param item input of user that come after command
     * @return a String array of size 3
     * @throws WrongSyntaxForCommandException thrown when Wrong Syntax for command is given
     */
    public static String[] parseEvent(String item) throws WrongSyntaxForCommandException {
        int fromLocation = item.indexOf(" /from ");
        int toLocation = item.indexOf(" /to ");
        String name;
        String start;
        String end;
        if (fromLocation == -1 || toLocation == -1) {
            throw new WrongSyntaxForCommandException(Command.EVENT);
        }
        if (fromLocation < toLocation) {
            name = item.substring(0, fromLocation).trim();
            start = item.substring(fromLocation + 7, toLocation).trim();
            end = item.substring(toLocation + 5).trim();
        } else {
            name = item.substring(0, toLocation).trim();
            end = item.substring(toLocation + 5, fromLocation).trim();
            start = item.substring(fromLocation + 7).trim();
        }
        if (name.isEmpty() | start.isEmpty() | end.isEmpty()) {
            throw new WrongSyntaxForCommandException(Command.EVENT);
        }
        return new String[] {name, start, end};
    }

    public static String[] DATE_PATTERNS = {
            // Original patterns
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd", "yyyyMMdd",
            "dd-MM-yyyy", "dd/MM/yyyy", "dd.MM.yyyy", "ddMMyyyy",
            "dd MMM yyyy", "dd MMMM yyyy", "MMM dd, yyyy", "MMMM dd, yyyy",
            "yyyy-MM", "yyyy/MM", "yyyy.MM", "yyyyMM",
            "MM-yyyy", "MM/yyyy", "MMyyyy",

            // Additional patterns with single M and d
            "yyyy-M-d", "yyyy/M/d", "yyyy.M.d",
            "d-M-yyyy", "d/M/yyyy", "d.M.yyyy", "dMyyyy",
            "d MMM yyyy", "d MMMM yyyy", "MMM d, yyyy", "MMMM d, yyyy",
            "yyyy-M", "yyyy/M", "yyyy.M",
            "M-yyyy", "M/yyyy", "Myyyy",

            // Additional patterns with yy for two-digit year format after MM or dd
            "dd-MM-yy", "dd/MM/yy", "dd.MM.yy", "ddMMyy",
            "dd MMM yy", "dd MMMM yy", "MMM dd, yy", "MMMM dd, yy",
            "MM-yy", "MM/yy", "MMyy",
            "d-M-yy", "d/M/yy", "d.M.yy", "dMyy",
            "d MMM yy", "d MMMM yy", "MMM d, yy", "MMMM d, yy",
            "M-yy", "M/yy", "Myy"
    };



    public static String[] DATE_TIME_PATTERNS = {
            // Original patterns
            "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS",
            "yyyy/MM/dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss", "yyyyMMdd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss", "dd-MM-yyyy HH:mm:ss",
            "yyyy-MM-dd HH:mm", "yyyy-MM-dd'T'HH:mm", "yyyy/MM/dd HH:mm",
            "dd-MM-yyyy HH:mm", "dd/MM/yyyy HH:mm",
            "dd MMM yyyy HH:mm:ss", "dd MMMM yyyy HH:mm:ss",
            "MMMM dd, yyyy HH:mm:ss", "MMM dd, yyyy HH:mm:ss",

            // Additional patterns with single M and d
            "yyyy-M-d'T'HH:mm:ss", "yyyy-M-d'T'HH:mm:ss.SSS",
            "yyyy-M-d'T'HH:mm:ss.SSSSSS", "yyyy-M-d'T'HH:mm:ss.SSSSSSSSS",
            "yyyy/M/d HH:mm:ss", "yyyy.M.d HH:mm:ss", "yyyyMd HH:mm:ss",
            "yyyy-M-d HH:mm:ss", "d-M-yyyy HH:mm:ss",
            "yyyy-M-d HH:mm", "yyyy-M-d'T'HH:mm", "yyyy/M/d HH:mm",
            "d-M-yyyy HH:mm", "d/M/yyyy HH:mm",
            "d MMM yyyy HH:mm:ss", "d MMMM yyyy HH:mm:ss",
            "MMMM d, yyyy HH:mm:ss", "MMM d, yyyy HH:mm:ss",

            // Additional patterns with yy for two-digit year format after MM or dd
            "dd-MM-yy HH:mm:ss", "dd/MM/yy HH:mm:ss", "dd.MM.yy HH:mm:ss", "ddMMyy HH:mm:ss",
            "dd-MM-yy HH:mm", "dd/MM/yy HH:mm", "dd.MM.yy HH:mm",
            "dd MMM yy HH:mm:ss", "dd MMMM yy HH:mm:ss",
            "MMMM dd, yy HH:mm:ss", "MMM dd, yy HH:mm:ss",
            "d-M-yy HH:mm:ss", "d/M/yy HH:mm:ss", "d.M.yy HH:mm:ss",
            "d-M-yy HH:mm", "d/M/yy HH:mm", "d.M.yy HH:mm",
            "d MMM yy HH:mm:ss", "d MMMM yy HH:mm:ss",
            "MMMM d, yy HH:mm:ss", "MMM d, yy HH:mm:ss"
    };



    public static LocalDate parseLocalDate(String str) throws ParseException {
        for (String pattern : DATE_PATTERNS) {
            try {
                return LocalDate.parse(str, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException e) {
                // Fallthrough
            }
        }
        throw new ParseException(str, 0);
    }

    public static LocalDateTime parseLocalDateTime(String str) throws ParseException {
        for (String pattern : DATE_TIME_PATTERNS) {
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException e) {
                // Fallthrough
            }
        }
        throw new ParseException(str, 0);
    }
}
