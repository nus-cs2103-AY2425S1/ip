package wiggly.commands;

import wiggly.task.TaskList;

import wiggly.util.Storage;
import wiggly.util.Ui;

/**
 * A class representation of the exit command
 */
public class ExitCommand extends Command {

	/**
	 * Creates a {@code ExitCommand} instance
	 */
	public ExitCommand() {
	}

	/**
	 * Executes the exit command by printing the exit message
	 * @param taskList The tasklist to execute the command on
	 * @param ui The user interface for printing status
	 * @param storage The storage file to save and load from
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {
		ui.printExit();
	}

	/**
	 * Returns {@code true} which can be used to terminate the program
	 * @return {@code true}
	 */
	@Override
	public boolean isExit() {
		return true;
	}
}