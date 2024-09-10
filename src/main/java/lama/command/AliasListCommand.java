package lama.command;

import lama.AliasManager;
import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;

/**
 * The AliasListCommand class is responsible for displaying the list of all user-defined aliases.
 * It extends the Command class and overrides the run method to show the aliases to the user.
 */
public class AliasListCommand extends Command {

    /**
     * Executes the AliasListCommand. This method retrieves the list of all aliases
     * and displays them via the user interface. The formatted list of aliases is returned.
     *
     * @param taskList Task list (Arraylist) on which the command operates.
     * @param storage Storage used to save or load task.
     * @param ui User interface that interacts with user.
     * @return A string message containing the formatted list of aliases.
     * @throws LamaException Thrown if an error occurs during the command execution.
     */
    @Override
    public String run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        ui.showAliasListCommand();
        return AliasManager.getFormattedAliasList();
    }

}
