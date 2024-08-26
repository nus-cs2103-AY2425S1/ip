package wiggly.commands;

import wiggly.task.TaskList;

import wiggly.util.Storage;
import wiggly.util.Ui;

/**
 * A class representation of a mark command
 */
public class MarkCommand extends Command {

	private final int taskNumber;
	private final boolean isDone;

	/**
	 * Creates a {@code MarkCommand} instance to mark a specified task from {@code TaskList} as done or undone
	 * @param taskNumber The corresponding task number to mark
	 * @param isDone The boolean expression to mark the task as done or undone
	 */
	public MarkCommand(int taskNumber, boolean isDone) {
		this.taskNumber = taskNumber;
		this.isDone = isDone;
	}

	/**
	 * Executes the mark command by marking the specified {@code Task} from {@code TaskList} as done or undone
	 * using {@code taskNumber} and {@code isDone}
	 * @param taskList The tasklist to execute the command on
	 * @param ui The user interface for printing status
	 * @param storage The storage file to save and load from
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {
		if (isDone) {
			ui.printWrappedString(taskList.markDone(taskNumber));
		} else {
			ui.printWrappedString(taskList.markUndone(taskNumber));
		}
		storage.save(taskList);
	}
}