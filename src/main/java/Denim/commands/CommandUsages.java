package denim.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an abstract class containing all the commands available in the program.
 */
public abstract class CommandUsages {

    public static final int TOTAL_USER_COMMANDS = 8;

    public static final List<String> COMMAND_USAGES = new ArrayList<>(Arrays.asList(
            DeadlineCommand.COMMAND_USAGE,
            TodoCommand.COMMAND_USAGE,
            EventCommand.COMMAND_USAGE,
            DeleteCommand.COMMAND_USAGE,
            ListCommand.COMMAND_USAGE,
            MarkCommand.COMMAND_USAGE,
            UnmarkCommand.COMMAND_USAGE,
            FindCommand.COMMAND_USAGE
    ));

    public static final List<String> COMMAND_EXAMPLES = new ArrayList<>(Arrays.asList(
            DeadlineCommand.COMMAND_EXAMPLE,
            TodoCommand.COMMAND_EXAMPLE,
            EventCommand.COMMAND_EXAMPLE,
            DeleteCommand.COMMAND_EXAMPLE,
            ListCommand.COMMAND_EXAMPLE,
            MarkCommand.COMMAND_EXAMPLE,
            UnmarkCommand.COMMAND_EXAMPLE,
            FindCommand.COMMAND_EXAMPLE
    ));
}
