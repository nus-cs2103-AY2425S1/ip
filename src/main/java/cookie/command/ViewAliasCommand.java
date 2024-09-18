package cookie.command;

import java.util.Hashtable;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * The {@code ViewAliasCommand} class is responsible for executing the action of viewing
 * all available command aliases from a provided Hashtable. This class extends the {@code Command}
 * class and overrides the {@code executeCommand} method to handle this specific functionality.
 */
public class ViewAliasCommand extends Command {
    private final Hashtable<String, String> commandAliasesMap;

    /**
     * Constructs a {@code ViewAliasCommand} object.
     *
     * @param commandAliasesMap the Hashtable containing command aliases as keys
     *                          and their corresponding commands as values.
     */
    public ViewAliasCommand(Hashtable<String, String> commandAliasesMap) {
        this.commandAliasesMap = commandAliasesMap;
    }
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws CookieException {
        return ui.printAllAlias(commandAliasesMap);
    }
}
