package wiggly.commands;

import wiggly.exception.WigglyException;

import wiggly.task.TaskList;

import wiggly.util.Storage;
import wiggly.util.Ui;

/**
 * An abstract representation of a Wiggly Command
 */
public abstract class Command {

	protected Command() {

	}

	/**
	 * Executes the command using information from taskList and storage, then prints out the operation status using
	 * ui.
	 * @param taskList The tasklist to execute the command on
	 * @param ui The user interface for printing status
	 * @param storage The storage file to save and load from
	 * @throws WigglyException If this method is not overwritten
	 */
	public void execute(TaskList taskList, Ui ui, Storage storage) throws WigglyException {
		throw new WigglyException("This command is not supported yet.");
	}

	/**
	 * Returns the boolean result of whether this {@code Command} will cause the program to terminate, {@code false}
	 * by default
	 * @return the boolean result of whether this {@code Command} will cause the program to terminate
	 */
	public boolean isExit() {
		return false;
	}
}
