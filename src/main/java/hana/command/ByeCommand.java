package hana.command;

import hana.tasklist.TaskList;
import hana.ui.Ui;
import hana.storage.Storage;
import hana.HanaException;

/**
 * Represents a command to exit the program.
 * When executed, this command will print a goodbye message and signal the application to exit.
 */
public class ByeCommand extends Command{

	/**
	 * Executes the ByeCommand which prints a goodbye message.
	 *
	 * @param taskList The list of tasks.
	 * @param ui The UI object used to interact with the user.
	 * @param storage The storage object to handle reading/writing of tasks.
	 * @throws HanaException If an error occurs during command execution.
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		ui.printBye();
	}

	/**
	 * Indicates that this command will exit the application.
	 *
	 * @return true.
	 */
	@Override
	public boolean isExit() {
		return true;
	}
}
