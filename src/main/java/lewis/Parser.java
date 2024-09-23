package lewis;

import java.util.Set;
/**
 * Implements a parser that interprets the user input string for Lewis to execute.
 */
class Parser {
    /** A set of valid commands for Lewis to execute */
    public static final Set<String> VALID_COMMANDS = Set.of("help", "mark", "unmark", "echo",
            "todo", "deadline", "event", "bye", "hello", "list", "delete", "exit", "find");

    /** Private constructor for Parser */
    private Parser() {
    }
    /**
     * Gets the command from the input string, compares it against the set of valid commands
     * and returns it if it is valid.
     * @param input the input string
     * @return the command if it is a valid command line. Otherwise, returns null.
     */
    private static String getCommand(String input) {
        for (String command : VALID_COMMANDS) {
            if (input.startsWith(command)) {
                return command;
            }
        }
        return null;
    }

    /**
     * Parses the input command, and returns a corresponding Command type
     * @param input The input read from the standard input stream
     * @return a Command corresponding to the input
     * @throws LewisException if the command is not recognised.
     */
    public static Command parseCommand(String input) throws LewisException {
        String command = getCommand(input);
        if (command == null) {
            throw new LewisException("Hey, I don't recognise that command. Try \"help\""
                    + " to find out what I can do.");
        }
        switch(command) {
        case "help" -> {
            return HelpCommand.of(input);
        }
        case "mark", "unmark" -> {
            return new MarkUnmarkCommand(input);
        }
        case "echo" -> {
            try {
                return new EchoCommand(input);
            } catch (LewisException e) {
                Ui.printString(e.getMessage());
            }
        }
        case "todo" -> {
            return new TodoCommand(input);
        }
        case "deadline" -> {
            return DeadlineCommand.of(input);
        }
        case "event" -> {
            try {
                return EventCommand.of(input);
            } catch (LewisException e) {
                Ui.printString(e.getMessage());
            }
        }
        case "bye", "exit" -> {
            return ByeCommand.of();
        }
        case "hello" -> {
            return HelloCommand.of();
        }
        case "list" -> {
            return ListCommand.of();
        }
        case "delete" -> {
            return DeleteCommand.of(input);
        }
        case "find" -> {
            return FindCommand.of(input);
        }
        default -> {
            //fall through
        }
        }
        return HelpCommand.of("help");
    }
}
