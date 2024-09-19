package lama.command;

import lama.AliasManager;
import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;

/**
 * The AliasCommand class is responsible for mapping an alias to an existing command.
 * This allows the user to define shorthand aliases for frequently used commands.
 * It extends the Command class and overrides the run method to implement its functionality.
 */
public class AliasCommand extends Command {
    private String alias;
    private String command;

    /**
     * Constructs an AliasCommand to map an alias to a command.
     *
     * @param alias The user-defined alias.
     * @param command The command that alias should map to.
     */
    public AliasCommand(String alias, String command) {
        this.alias = alias;
        this.command = command;
    }

    /**
     * Executes the alias command. This method checks if the provided command is valid.
     * If valid, the alias is mapped to the command using the AliasManager.
     * If the command is invalid, a LamaException is thrown.
     *
     * @param taskList Task list (Arraylist) on which the command operates.
     * @param storage Storage used to save or load task.
     * @param ui User interface that interacts with user.
     * @return A string message confirming that the alias has been set.
     * @throws LamaException Thrown if the command is invalid or an error occurs during alias creation.
     */
    @Override
    public String run(TaskList taskList, Storage storage, Ui ui) throws LamaException {

        if (!CommandType.isValidCommand(command)) {
            throw new LamaException("Invalid command: '" + command + "'. Cannot set alias.");
        }

        AliasManager.setAlias(alias, command);
        ui.showAliasCommand(alias, command);
        return "Alias set: " + alias + " -> " + command;
    }
}
