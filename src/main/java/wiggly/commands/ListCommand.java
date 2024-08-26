package wiggly.commands;

import wiggly.task.TaskList;

import wiggly.util.Storage;
import wiggly.util.Ui;

/**
 * A class representation of a list command
 */
public class ListCommand extends Command {


	/**
	 * Executes the list command by printing out the string representation of {@code TaskList}
	 * @param taskList The tasklist to execute the command on
	 * @param ui The user interface for printing status
	 * @param storage The storage file to save and load from
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {
		String str = Ui.TASK_HEADER + "\n" +
				taskList.toString();
		ui.printWrappedString(str);
	}
}
