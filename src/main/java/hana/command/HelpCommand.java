package hana.command;

import hana.HanaException;
import hana.storage.Storage;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * Represents a command to print all commands.
 * When executed, this command will print commands and their usage.
 */
public class HelpCommand extends Command {

	/**
	 * Executes the command to print all commands.
	 *
	 * @param taskList The list of tasks.
	 * @param ui The UI object used to interact with the user.
	 * @param storage The storage object to handle reading/writing of tasks.
	 * @throws HanaException If an error occurs during command execution.
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		ui.printCommands();
	}
}
