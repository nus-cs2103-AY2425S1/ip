package hana.command;

import hana.HanaException;
import hana.storage.Storage;
import hana.tasklist.TaskList;
import hana.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand extends Command {
	private final String keyword;

	/**
	 * Constructs a FindCommand.
	 *
	 * @param input Input from user.
	 */
	public FindCommand(String input) throws HanaException {
		String[] parts = input.split(" ", 2);
		if (parts.length < 2) {
			throw new HanaException("Please provide a keyword to search.");
		}
		this.keyword = parts[1].trim();
	}

	/**
	 * Executes the find command.
	 *
	 * @param taskList The list of tasks.
	 * @param ui The UI object used to interact with the user.
	 * @param storage The storage object to handle reading/writing of tasks.
	 * @throws HanaException If an error occurs during command execution.
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
		ui.printTasksFound(taskList.findTasksByKeyword(keyword));
	}
}
