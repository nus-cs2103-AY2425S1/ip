package snah.commands;

import snah.TaskList;
import snah.util.Parser;
import snah.util.Storage;

/**
 * Invalid command to handle invalid commands displays all available commands
 */
public class Invalid extends Command {
    public Invalid() {
        super("");
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String invalidCommand = Parser.getRawCommand(getInput());
        String response = String.format("Oi, no such command \"%s\". Try these instead\n", invalidCommand);
        response += Parser.getCommandStrings();
        return response;
    }

}
