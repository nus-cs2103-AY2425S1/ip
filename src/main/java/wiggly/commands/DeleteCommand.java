package wiggly.commands;

import wiggly.task.TaskList;

import wiggly.util.Storage;
import wiggly.util.Ui;
/**
 * A class representation of the delete command
 */
public class DeleteCommand extends Command {

	private final int taskNumber;

	/**
	 * Creates a {@code DeleteCommand} instance to delete a specified task from {@code TaskList}
	 * @param taskNumber The corresponding task number to delete
	 */
	public DeleteCommand(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	/**
	 * Executes the delete command by removing the specified {@code Task} from {@code TaskList} using {@code taskNumber}
	 * @param taskList The tasklist to execute the command on
	 * @param ui The user interface for printing status
	 * @param storage The storage file to save and load from
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {
		ui.printWrappedString(taskList.deleteTask(taskNumber));
		storage.save(taskList);
	}
}
