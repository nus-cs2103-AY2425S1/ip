package wiggly.commands;

import wiggly.task.TaskList;

import wiggly.util.Storage;
import wiggly.util.Ui;

public class ExitCommand extends Command {

	public ExitCommand() {
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {
		ui.printExit();
	}

	@Override
	public boolean isExit() {
		return true;
	}
}