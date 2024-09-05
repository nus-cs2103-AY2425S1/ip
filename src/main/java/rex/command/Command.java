package rex.command;

import rex.exception.InvalidCommandException;

/**
 * The {@code Command} enum represents the various commands that can be issued in the Rex application.
 * Each command corresponds to a specific action that the application can perform.
 */
public enum Command {
    TODO, DEADLINE, EVENT, LIST, FIND, MARK, UNMARK, DELETE, RAWR, BYE, HELP;

    /**
     * Converts a user input string into the corresponding {@code Command} enum.
     *
     * @param input The input string representing the command.
     * @return The corresponding {@code Command} enum.
     * @throws InvalidCommandException if the input string does not match any valid command.
     */
    public static Command inputToCommand(String input) throws InvalidCommandException {
        switch (input) {
        case "help":
            return HELP;
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "list":
            return LIST;
        case "find":
            return FIND;
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "delete":
            return DELETE;
        case "rawr":
            return RAWR;
        case "bye":
            return BYE;
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Returns a usage message for the specified {@code Command}.
     * The usage message provides a brief description of how to use the command.
     *
     * @param command The {@code Command} for which the usage message is requested.
     * @return A string containing the usage message for the command.
     */
    public static String usageMessage(Command command) {
        switch (command) {
        case HELP:
            return "help";
        case TODO:
            return "todo <description>";
        case DEADLINE:
            return "deadline <description> /by <date: DD-MM-YY> <time: HHMM>";
        case EVENT:
            return "event <description> /from <date: DD-MM-YY> <time: HHMM> /to <date: DD-MM-YY> <time: HHMM>";
        case LIST:
            return "list";
        case FIND:
            return "find <keyword>";
        case MARK:
            return "mark <task number>";
        case UNMARK:
            return "unmark <task number>";
        case DELETE:
            return "delete <task number>";
        case RAWR:
            return "rawr";
        case BYE:
            return "bye";
        default:
        }
        return null;
    }

    /**
     * Provides a list of all valid commands and their corresponding usage messages.
     *
     * @return A formatted string listing all valid commands and their usage instructions.
     */
    public static String getCommandList() {
        String spaces = "  ";
        String output = "";
        output += "Here are a list of valid commands and how to use them:\n";
        output += "\n";

        output += spaces + "Adding tasks:\n";
        output += spaces + "* " + usageMessage(TODO) + "\n";
        output += spaces + "* " + usageMessage(DEADLINE) + "\n";
        output += spaces + "* " + usageMessage(EVENT) + "\n";
        output += "\n";

        output += spaces + "List all tasks:\n";
        output += spaces + "* " + usageMessage(LIST) + "\n";
        output += "\n";

        output += spaces + "Find tasks with description that matches keyword:\n";
        output += spaces + "* " + usageMessage(FIND) + "\n";
        output += "\n";

        output += spaces + "Mark/Unmark/Delete tasks:\n";
        output += spaces + "* " + usageMessage(MARK) + "\n";
        output += spaces + "* " + usageMessage(UNMARK) + "\n";
        output += spaces + "* " + usageMessage(DELETE) + "\n";
        output += "\n";

        output += spaces + "rawr:\n";
        output += spaces + "* " + usageMessage(RAWR) + "\n";
        output += "\n";

        output += spaces + "Exit:\n";
        output += spaces + "* " + usageMessage(BYE) + "\n";

        return output;
    }
}
