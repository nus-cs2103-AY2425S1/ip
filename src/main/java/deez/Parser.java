package deez;

import javafx.util.Pair;

import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Parser {

    private static final DateTimeFormatter dateTimeInputFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

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
            default -> Command.UNKNOWN;
        };

        Properties props = switch (cmd) {
            case TODO -> parseTodoProps(propString);
            case EVENT -> parseEventProps(propString);
            case DEADLINE -> parseDeadlineProps(propString);
            case MARK, UNMARK, DELETE -> parseTaskIndexProp(propString);
            case EXIT, LIST, SAVE, UNKNOWN -> null;
        };

        return new Pair<>(cmd, props);
    }

    private static Properties parseTaskIndexProp(String propString) {
        Properties props = new Properties();
        props.setProperty("index", propString.strip());
        return props;
    }

    private static Properties parseTodoProps(String propString) {
        Properties props = new Properties();
        if (propString.isBlank()) {
            throw new DeezException("Please provide a description for the todo.");
        }
        props.setProperty("name", propString.strip());
        return props;
    }

    private static Properties parseEventProps(String propString) {
        Properties props = new Properties();
        String[] parts = propString.split("/from|/to", 3);
        if (parts.length != 3 || parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
            throw new DeezException("Please provide a description, start date, and end date.", "Usage:", "event " +
                    "project meeting /from 2019-10-15 1800 /to 2019-10-15 1900");
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
            throw new DeezException("Please provide a description and deadline.", "Usage:", "deadline " +
                    "return book" +
                    " /by 2019-10-15 1800");
        }
        if (parts[0].strip().isBlank() || parts[1].strip().isBlank()) {
            throw new DeezException("Description and deadline must not be blank.", "Usage:", "deadline " +
                    "return book" +
                    " /by 2019-10-15 1800");
        }
        props.setProperty("name", parts[0].strip());
        props.setProperty("by", parts[1].strip());
        return props;
    }

}
