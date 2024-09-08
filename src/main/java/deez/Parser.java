package deez;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javafx.util.Pair;


/**
 * This class is used for parsing user input.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * This method parses the given string and returns a pair containing the command and properties.
     *
     * @param input The input string to be parsed.
     * @return A pair containing the command and properties.
     */
    static Pair<Command, Properties> parse(String input) {
        String[] inputStringSplit = input.split(" ", 2);
        String cmdString = inputStringSplit[0];
        String propString = inputStringSplit.length == 2 ? inputStringSplit[1] : "";

        // Parse command
        Command cmd = switch (cmdString) {
        case "bye" -> Command.EXIT;
        case "list" -> Command.LIST;
        case "mark" -> Command.MARK;
        case "unmark" -> Command.UNMARK;
        case "todo" -> Command.TODO;
        case "deadline" -> Command.DEADLINE;
        case "event" -> Command.EVENT;
        case "delete" -> Command.DELETE;
        case "save" -> Command.SAVE;
        case "find" -> Command.FIND;
        default -> Command.UNKNOWN;
        };

        Properties props = switch (cmd) {
        case TODO -> parseTodoProps(propString);
        case EVENT -> parseEventProps(propString);
        case DEADLINE -> parseDeadlineProps(propString);
        case MARK, UNMARK, DELETE -> parseTaskIndexProp(propString);
        case FIND -> parseTextInputProps(propString);
        case EXIT, LIST, SAVE, UNKNOWN -> new Properties();
        };

        return new Pair<>(cmd, props);
    }

    private static Properties parseTextInputProps(String propString) {
        Properties props = new Properties();
        props.setProperty("keyword", propString.strip());
        return props;
    }

    private static Properties parseTaskIndexProp(String propString) {
        Properties props = new Properties();
        props.setProperty("index", propString.strip());
        return props;
    }

    private static Properties parseTodoProps(String propString) {
        Properties props = new Properties();
        props.setProperty("name", propString.strip());
        return props;
    }

    private static Properties parseEventProps(String propString) {
        Properties props = new Properties();
        String[] parts = propString.split("/from|/to", 3);
        if (parts.length != 3 || parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
            throw new DeezException(
                "Please provide a description, start date, and end date.",
                "Usage:",
                "event " + "project meeting /from 2019-10-15 1800 /to 2019-10-15 1900");
        }
        props.setProperty("name", parts[0].strip());
        props.setProperty("from", parts[1].strip());
        props.setProperty("to", parts[2].strip());
        return props;
    }

    private static Properties parseDeadlineProps(String propString) {
        Properties props = new Properties();
        String[] parts = propString.split("/by", 2);
        if (parts.length != 2) {
            throw new DeezException(
                "Please provide a description and deadline.",
                "Usage:",
                "deadline " + "return book" + " /by 2019-10-15 1800");
        }
        if (parts[0].strip().isBlank() || parts[1].strip().isBlank()) {
            throw new DeezException(
                "Description and deadline must not be blank.",
                "Usage:",
                "deadline " + "return book" + " /by 2019-10-15 1800");
        }
        props.setProperty("name", parts[0].strip());
        props.setProperty("by", parts[1].strip());
        return props;
    }
    /**
     * This method parses the given date-time string into a LocalDateTime object.
     *
     * @param dateTimeString The date-time string to be parsed.
     * @return A LocalDateTime object.
     */
    public static LocalDateTime parseDateTimeString(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DATE_TIME_INPUT_FORMATTER);
    }
}
