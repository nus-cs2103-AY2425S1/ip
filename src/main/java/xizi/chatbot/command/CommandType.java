package xizi.chatbot.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enum representing various types of commands that can be processed by the chatbot.
 * Each command type is associated with a regular expression pattern used to match user input.
 */
public enum CommandType {
    /**
     * Represents the "mark" command, which marks a task as completed.
     * The command expects a task number as an argument.
     * Example: "mark 1"
     */
    MARK("^mark (\\d+)$"),

    /**
     * Represents the "unmark" command, which unmarks a task, indicating it is not completed.
     * The command expects a task number as an argument.
     * Example: "unmark 1"
     */
    UNMARK("^unmark (\\d+)$"),

    DELETE_DONE("^delete done$"),

    /**
     * Represents the "delete" command, which deletes a task.
     * The command expects a task number as an argument.
     * Example: "delete 1"
     */
    DELETE("^delete\\s+(\\d+)$"),

    /**
     * Represents the "todo" command, which adds a new task without a deadline or event time.
     * The command expects a description of the task.
     * Example: "todo read book"
     */
    TODO("^todo\\s*(.*)$"),

    /**
     * Represents the "deadline" command, which adds a new task with a specific deadline.
     * The command expects a description of the task and a deadline.
     * Example: "deadline return book /by 2/12/2019 1800"
     */
    DEADLINE("^deadline\\s*(.*?)\\s*/by\\s*(.*?)$"),

    /**
     * Represents the "event" command, which adds a new task with a start and end time.
     * The command expects a description of the task, start time, and end time.
     * Example: "event project meeting /from 2/12/2019 1400 /to 2/12/2019 1600"
     */
    EVENT("^event\\s*(.*?)\\s*/from\\s*(.*?)\\s*/to\\s*(.*?)$"),

    /**
     * Represents the "list" command, which lists all tasks.
     * Example: "list"
     */
    LIST("^list$"),

    /**
     * Represents the "bye" command, which exits the chatbot.
     * Example: "bye"
     */
    BYE("^bye$"),

    /**
     * Represents the "help" command, which displays the list of available commands.
     * Example: "help"
     */
    HELP("^help$"),

    /**
     * Represents the "list on" command, which lists all tasks due on a specific date and time.
     * The command expects a date and time in the format "dd/MM/yyyy HHmm".
     * Example: "list on 2/12/2019 1800"
     */
    LIST_ON("^list on (\\d{1,2}/\\d{1,2}/\\d{4} \\d{4})$"),

    /**
     * Represents the "find" command, which searches for tasks that contain a specific keyword.
     * The command expects a keyword to search for.
     * Example: "find book"
     */
    FIND("^find\\s*(.*)$"),

    /**
     * Represents an unknown command that does not match any of the defined patterns.
     */
    UNKNOWN("");

    private final Pattern pattern;

    /**
     * Constructor to associate a regular expression pattern with the command type.
     *
     * @param regex The regular expression used to match the command.
     */
    CommandType(String regex) {
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Returns a matcher for the given input based on the command's pattern.
     *
     * @param input The input string to match against the command's pattern.
     * @return A {@code Matcher} object for the input.
     */
    public Matcher matcher(String input) {
        return pattern.matcher(input);
    }

    /**
     * Determines the command type based on the given input.
     *
     * @param input The input string to match against all command patterns.
     * @return The corresponding {@code CommandType} if a match is found; otherwise, {@code UNKNOWN}.
     */
    public static CommandType fromInput(String input) {
        for (CommandType type : values()) {
            if (type.matcher(input).matches()) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
