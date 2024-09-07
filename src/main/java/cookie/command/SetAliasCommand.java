package cookie.command;

import java.util.Hashtable;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents a command to set an alias for an existing command.
 * This command updates the command alias mapping, allowing users to use custom aliases
 * for predefined commands.
 */
public class SetAliasCommand extends Command {

    private final String command;
    private final String alias;
    private final Hashtable<String, String> commandAliasesMap;

    /**
     * Constructs a {@code SetAliasCommand} object with the specified command, alias, and alias map.
     *
     * @param command the original command to be aliased
     * @param alias the alias to be set for the command
     * @param commandAliasesMap the map containing command aliases
     */
    public SetAliasCommand(String command, String alias, Hashtable<String, String> commandAliasesMap) {
        this.command = command;
        this.alias = alias;
        this.commandAliasesMap = commandAliasesMap;
    }
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws CookieException {
        commandAliasesMap.put(alias, command);
        return ui.setAsAliasSuccess(command, alias);
    }
}
