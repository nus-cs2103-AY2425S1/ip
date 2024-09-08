package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;

/**
 * A class representing the list command to list out tasks.
 */
public class ResetAliasCommand extends Command {
    private CommandList commandList;

    /**
     * Constructor for SetAliasCommand class
     * @param commandList
     */
    public ResetAliasCommand(CommandList commandList) {
        super();
        this.commandList = commandList;
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) {
        return this.commandList.resetToDefault();
    }

    @Override
    public String commandDescription() {
        return "Resets the command list to its default setting";
    }

    @Override
    public String toString() {
        return "ResetAliasCommand";
    }
}
