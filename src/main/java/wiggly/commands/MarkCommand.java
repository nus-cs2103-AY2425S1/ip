package wiggly.commands;

import wiggly.task.TaskList;

import wiggly.util.Storage;
import wiggly.util.Ui;

import wiggly.exception.WigglyException;

public class MarkCommand extends Command {

	private final int taskNumber;
	private final boolean isDone;

	public MarkCommand(int taskNumber, boolean isDone) {
		this.taskNumber = taskNumber;
		this.isDone = isDone;
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws WigglyException {
		if (isDone) {
			ui.printWrappedString(taskList.markDone(taskNumber));
		} else {
			ui.printWrappedString(taskList.markUndone(taskNumber));
		}
		storage.save(taskList);
	}
}