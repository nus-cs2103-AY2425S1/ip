package lama.command;

import lama.AliasManager;
import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;

/**
 * Represents a command to delete an alias.
 * This command removes the specified alias from the alias map
 * and updates the persistent storage.
 */
public class DeleteAliasCommand extends Command {

    private String alias;

    /**
     * Constructs a DeleteAliasCommand with the alias to be deleted.
     *
     * @param alias The alias to be deleted.
     */
    public DeleteAliasCommand(String alias) {
        this.alias = alias;
    }

    /**
     * Executes the DeleteAliasCommand. This method deletes the alias from
     * the AliasManager and updates the user interface with a confirmation message.
     *
     * @param taskList Task list (Arraylist) on which the command operates.
     * @param storage Storage used to save or load task.
     * @param ui User interface that interacts with user.
     * @return A string message confirming that the alias has been deleted.
     * @throws LamaException Thrown if there is an issue during alias deletion.
     */
    @Override
    public String run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        AliasManager.deleteAlias(alias);
        ui.showDeleteAliasCommand(alias);
        return "Alias '" + alias + "' has been deleted.";
    }

}
