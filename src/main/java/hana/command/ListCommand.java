package hana.command;

import hana.HanaException;
import hana.storage.Storage;
import hana.tasklist.TaskList;
import hana.ui.Ui;

public class ListCommand extends Command {

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		ui.printTasks(taskList.getTasks());
	}
}
