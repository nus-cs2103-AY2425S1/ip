package wiggly.commands;

import wiggly.exception.WigglyException;

import wiggly.task.TaskList;

import wiggly.util.Storage;
import wiggly.util.Ui;


public abstract class Command {

	protected Command() {

	}

	public void execute(TaskList taskList, Ui ui, Storage storage) throws WigglyException {
		throw new WigglyException("This command is not supported yet.");
	}

	public boolean isExit() {
		return false;
	}
}
