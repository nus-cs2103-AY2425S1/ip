package hana.command;

import hana.HanaException;
import hana.storage.Storage;
import hana.tasklist.TaskList;
import hana.ui.Ui;

public class UnmarkCommand extends Command{
	private int taskNumber;

	public UnmarkCommand(String input) throws HanaException {
		String[] parts = input.split(" ", 2);
		if (parts.length < 2 || parts[1].trim().isEmpty()) {
			throw new HanaException("Task number cannot be empty.");
		}
		this.taskNumber = Integer.parseInt(parts[1].trim());
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		if (taskList.isEmpty()) {
			throw new HanaException("No tasks added yet. Add a task first!");
		}
		taskList.markTask(taskNumber, false);
		ui.printMarked(taskList.getTasks().get(taskNumber - 1),false);
		storage.save();
	}
}
