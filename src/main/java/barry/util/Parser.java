package barry.util;

import barry.command.*;

/**
 * Represents a parser that interprets user input and returns the corresponding command.
 */
public class Parser {
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UNKNOWN, HELP
    }

    /**
     * Parses the user's input and returns the corresponding command.
     *
     * @param input The user's input.
     * @return The command corresponding to the user's input.
     */
    public static Command parse(String input) {
        assert input != null : "command cannot be null!";
        String[] split = input.split(" ", 2);
        String command = split[0].toUpperCase();
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            commandType = CommandType.UNKNOWN;
        }

        switch (commandType) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    return new MarkCommand(i);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("bzz~ Unknown command :o There is no task listed number this index ");
                }
            case UNMARK:
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    return new UnmarkCommand(i);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("bzz~ Unknown command :o There is no task listed number this index ");
                }
            case TODO:
                if (split.length < 2 || split[1].trim().isEmpty()) {
                    return new UnknownCommand("Unknown command... Description seems to be empty" +
                            "\nPlease use the following format: todo <description> ");
                }
                return new TodoCommand(split[1]);
            case DEADLINE:
                try {
                    String[] parts = split[1].split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        return new UnknownCommand("Unknown date bzz~ " +
                                "\nPlease use the following format: deadline <description> /by <yyyy-mm-dd> ");
                    }
                    return new DeadlineCommand(parts[0].trim(), parts[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("What is the task description? " +
                            "\nPlease use the following format: deadline <description> /by <yyyy-mm-dd> ");
                }
            case EVENT:
                try {
                    String[] parts = split[1].split(" /from ");
                    if (parts.length < 2) {
                        return new UnknownCommand("What is the start time and/or date? " +
                                "\nPlease use the following format: " +
                                "\nevent <description> /from <__ am/pm> /to <__am/pm>" +
                                "\nOR event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>" +
                                "\nOR event <description> /from <yyyy-mm-dd> <__am/pm> /to <yyyy-mm-dd> <__am/pm>");
                    }
                    String[] times = parts[1].split(" /to ");
                    if (times.length < 2) {
                        return new UnknownCommand("What is the end time and/or date? " +
                                "\nPlease use the following format: " +
                                "\nevent <description> /from <__ am/pm> /to <__am/pm>" +
                                "\nOR event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>" +
                                "\nOR event <description> /from <yyyy-mm-dd> <__am/pm> /to <yyyy-mm-dd> <__am/pm>");
                    }
                    return new EventCommand(parts[0].trim(), times[0].trim(), times[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("What is the task description? " +
                            "\nPlease use the following format: " +
                            "\nevent <description> /from <__ am/pm> /to <__am/pm>" +
                            "\nOR event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>" +
                            "\nOR event <description> /from <yyyy-mm-dd> <__am/pm> /to <yyyy-mm-dd> <__am/pm>");
                }
            case DELETE:
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    return new DeleteCommand(i);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("bzz~ Unknown command :o There is no task listed under this index ");
                }
            case FIND:
                if (split.length < 2 || split[1].trim().isEmpty()) {
                    return new UnknownCommand("Unknown command..." +
                            "\nPlease use the following format:" +
                            "\n find <keyword>");
                }
                return new FindCommand(split[1].trim());
            case HELP:
                return new HelpCommand();
            default:
                return new UnknownCommand("Unknown command..." +
                        "Use 'Help' command for more information bzz~");
        }
    }
}
