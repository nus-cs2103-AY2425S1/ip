package Parser;

import Commands.Command;
import Commands.CommandManager;

public class CommandParser {
    private final CommandManager manager;
    public CommandParser() {
        manager = new CommandManager();
    }
    public Command parse(String input) {
        String commandStr = input.split(" ")[0];
        return manager.getCommand(commandStr);
    }
}
