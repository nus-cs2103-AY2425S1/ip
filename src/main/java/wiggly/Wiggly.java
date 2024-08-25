package wiggly;

import java.io.FileNotFoundException;

import wiggly.commands.Command;

import wiggly.exception.WigglyException;

import wiggly.parser.Parser;
import wiggly.task.TaskList;
import wiggly.util.Storage;
import wiggly.util.Ui;

public class Wiggly {

	private final Storage storage;
	private TaskList taskList;
	private final Ui ui;

	public Wiggly(String filePath) {
		ui = new Ui();
		storage = new Storage(filePath);
		try {
			taskList = new TaskList(storage.load());
		} catch (WigglyException | FileNotFoundException e) {
			ui.printWrappedString("Cannot load storage from " + filePath);
			taskList = new TaskList();
		}
	}

	public void run() {

		ui.printGreeting();
		boolean isExit = false;

		while (!isExit) {
			try {
				String fullCommand = ui.readCommand();
				Command c = Parser.parse(fullCommand);
				c.execute(taskList, ui, storage);
				isExit = c.isExit();
			} catch (WigglyException e) {
				ui.showError(e.getMessage());
			}
		}
	}

    public static void main(String[] args) {
		new Wiggly("./data/Wiggly.txt").run();
    }

}
