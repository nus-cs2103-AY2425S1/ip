package wiggly.commands;

import wiggly.task.TaskList;

import wiggly.util.Storage;
import wiggly.util.Ui;

public class DeleteCommand extends Command {

	private final int taskNumber;

	public DeleteCommand(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {
		ui.printWrappedString(taskList.deleteTask(taskNumber));
		storage.save(taskList);
	}
}
