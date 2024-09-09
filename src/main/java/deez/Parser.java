package deez;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;


/**
 * This class is used for parsing user input.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final Pattern NAME_REGEX = Pattern.compile("([^/#]* )|([^/#]*)", Pattern.MULTILINE);
    private static final Pattern PROPS_REGEX = Pattern.compile("(\\/([^\\s]+) ([^\\/#]+))", Pattern.MULTILINE);
    private static final Pattern TAGS_REGEX = Pattern.compile("#([^\\s\\/#]+)", Pattern.MULTILINE);


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

        Properties props = new Properties();

        // Parse properties
        Matcher propsMatcher = PROPS_REGEX.matcher(propString);
        Matcher nameMatcher = NAME_REGEX.matcher(propString);

        Matcher tagsMatcher = TAGS_REGEX.matcher(propString);

        while (propsMatcher.find()) {
            props.setProperty(propsMatcher.group(2).strip(), propsMatcher.group(3).strip());
        }
        if (nameMatcher.find()) {
            props.setProperty("name", nameMatcher.group(0).strip());
        }

        while (tagsMatcher.find()) {
            System.out.println("Full match: " + tagsMatcher.group(0));
            for (int i = 1; i <= tagsMatcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + tagsMatcher.group(i));
            }
        }

        // Validate properties
        switch (cmd) {
        case EVENT:
            validateEventProps(props);
            break;
        case DEADLINE:
            validateDeadlineProps(props);
            break;
        case MARK, UNMARK, DELETE:
            validateTaskIndexProp(props);
            break;
        case FIND:
            validateTextInputProps(props);
            break;
        default:
            break;
        }

        System.out.println(props);

        return new Pair<>(cmd, props);
    }
    /**
     * This method checks if the properties do not contain certain keys.
     *
     * @param props The properties to be checked
     * @param keys The keys that should not be present in the properties
     * @return True if the properties do not contain the given keys, false otherwise
     */
    private static boolean propsNotContains(Properties props, String... keys) {
        for (String key : keys) {
            if (!props.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if the properties contain blank values for certain keys.
     *
     * @param props The properties to be checked
     * @param keys The keys that should have non-blank values in the properties
     * @return True if the properties contain blank values for the given keys, false otherwise
     */
    private static boolean propsAreBlank(Properties props, String... keys) {
        for (String key : keys) {
            assert props.containsKey(key);
            if (props.getProperty(key).isBlank()) {
                return true;
            }
        }
        return false;
    }

    private static void validateTextInputProps(Properties props) {
        props.setProperty("keyword", props.getProperty("name"));
        props.remove("name");
    }

    private static void validateTaskIndexProp(Properties props) {
        props.setProperty("index", props.getProperty("name"));
        props.remove("name");
    }

    private static void validateEventProps(Properties props) {
        if (propsNotContains(props, "name", "from", "to") || propsAreBlank(props, "name", "from", "to")) {
            throw new DeezException("Please provide a description, start date, and end date.", "Usage:",
                "event " + "project meeting /from 2019-10-15 1800 /to 2019-10-15 1900");
        }
    }

    private static void validateDeadlineProps(Properties props) {
        if (propsNotContains(props, "name", "by") || propsAreBlank(props, "name", "by")) {
            throw new DeezException("Description and deadline must not be blank.", "Usage:",
                "deadline " + "return book" + " /by 2019-10-15 1800");
        }
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
