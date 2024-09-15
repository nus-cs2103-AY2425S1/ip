package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;

/**
 * A class representing the unbind command.
 */
public class UnbindAliasCommand extends Command {
    private CommandList commandList;

    /**
     * Constructor for UnbindAliasCommand class
     * @param commandList
     */
    public UnbindAliasCommand(CommandList commandList) {
        super();
        this.commandList = commandList;
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) {
        String alias = parser.getAliasToUnbind();
        return this.commandList.unbindCustomCommandAlias(alias);
    }

    @Override
    public String commandDescription() {
        return "Unbinds a specified alias to a chosen command, FORMAT: unbind [ALIAS]";
    }

    @Override
    public String toString() {
        return "UnbindAliasCommand";
    }
}
