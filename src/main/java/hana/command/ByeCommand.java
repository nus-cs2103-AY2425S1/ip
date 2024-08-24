package hana.command;

import hana.tasklist.TaskList;
import hana.ui.Ui;
import hana.storage.Storage;
import hana.HanaException;

public class ByeCommand extends Command{

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		ui.printBye();
	}

	@Override
	public boolean isExit() {
		return true;
	}
}
