package seedu.avo.parser;

import seedu.avo.commands.Command;
import seedu.avo.commands.CommandManager;

/**
 * Represents a parser that handles raw user input
 */
public class CommandParser {
    private final CommandManager manager;
    public CommandParser(CommandManager manager) {
        this.manager = manager;
    }

    /**
     * Returns a Command that corresponds to a given user input
     * @param input A raw String input from user
     * @return A Command from a store of recognised commands
     */
    public Command parse(String input) {
        String commandStr = input.split(" ")[0];
        return manager.getCommand(commandStr);
    }
}
