package wiggly.commands;

import wiggly.task.TaskList;
import wiggly.util.Storage;
import wiggly.util.Ui;

public class FindCommand extends Command{

	private final String keyword;

	public FindCommand(String description) {
		this.keyword = description;
	}

	/**
	 * Executes the find command by printing the filtered task list
	 * @param taskList The tasklist to execute the command on
	 * @param ui The user interface for printing status
	 * @param storage The storage file to save and load from
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {
		String str = Ui.SEARCH_HEADER + "\n" + taskList.search(this.keyword);
		ui.printWrappedString(str);
	}
}
