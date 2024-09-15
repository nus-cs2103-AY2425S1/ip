package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;

/**
 * A class representing the bind command.
 */
public class BindAliasCommand extends Command {
    private CommandList commandList;

    /**
     * Constructor for BindAliasCommand class
     * @param commandList
     */
    public BindAliasCommand(CommandList commandList) {
        super();
        this.commandList = commandList;
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) {
        String[] aliasAndCommand = parser.getAliasAndCommand();
        return this.commandList.bindCustomCommandAlias(aliasAndCommand[0], aliasAndCommand[1]);
    }

    @Override
    public String commandDescription() {
        return "Binds a specified alias to a chosen command, FORMAT: bind [ALIAS] [COMMAND_TO_ALIAS]";
    }

    @Override
    public String toString() {
        return "BindAliasCommand";
    }
}
