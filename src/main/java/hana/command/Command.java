package hana.command;

import hana.tasklist.TaskList;
import hana.ui.Ui;
import hana.storage.Storage;
import hana.HanaException;

public abstract class Command {

	public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException;

	public boolean isExit() {
		return false;
	}
}
