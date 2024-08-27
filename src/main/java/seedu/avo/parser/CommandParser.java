package seedu.avo.parser;

import seedu.avo.commands.Command;
import seedu.avo.commands.CommandManager;

public class CommandParser {
    private final CommandManager manager;
    public CommandParser(CommandManager manager) {
        this.manager = manager;
    }
    public Command parse(String input) {
        String commandStr = input.split(" ")[0];
        return manager.getCommand(commandStr);
    }
}
